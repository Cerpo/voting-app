package com.example.voting.service;

import com.example.voting.exception.ApiException;
import com.example.voting.model.vote.*;
import com.example.voting.payload.getaveragevotes.GetAverageVotesResponse;
import com.example.voting.payload.getvote.GetVoteResponse;
import com.example.voting.payload.getvoting.GetVotingResponse;
import com.example.voting.payload.getvotingsbyday.GetVotingsByDayResponse;
import com.example.voting.payload.getvotingsbyday.VoteResp;
import com.example.voting.payload.getvotingsbyday.VotingResp;
import com.example.voting.payload.savevoting.SaveVotingRequest;
import com.example.voting.payload.savevoting.SaveVotingResponse;
import com.example.voting.util.AppUtils;
import com.example.voting.util.DateUtil;
import com.example.voting.util.NumberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VoteService {
    private final VotingRepository votingRepository;

    public SaveVotingResponse saveVoting(SaveVotingRequest request) {
        Voting voting = Voting.convertRequest(request);

        votingRepository.findByDate(voting.getDate()).ifPresent(v ->
        { throw new ApiException(HttpStatus.BAD_REQUEST, "There is already a voting with the specified time."); });

        if (!validateVoting(voting)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid votes.");
        }

        return new SaveVotingResponse(votingRepository.save(voting).getVotingId().toString());
    }

    private boolean validateVoting(Voting voting) {
        Set<String> codeOfMPs = new HashSet<>();
        String president = voting.getPresident();

        for (Vote vote: voting.getVotes()) {
            codeOfMPs.add(vote.getCodeOfMP());
        }

        return (codeOfMPs.size() == voting.getVotes().size()) && codeOfMPs.contains(president);
    }

    public GetVoteResponse getVote(String votingId, String codeOfMP) {
        Voting voting = votingRepository.findById(Integer.valueOf(votingId)).orElseThrow(() ->
                new ApiException(HttpStatus.NOT_FOUND, "Voting with the specified parameters does not exist."));
        return new GetVoteResponse(getReplyOfMP(voting, codeOfMP));
    }

    private VoteReply getReplyOfMP(Voting voting, String codeOfMP) {
        for (Vote vote : voting.getVotes()) {
            if (vote.getCodeOfMP().equals(codeOfMP)) {
                return vote.getVoteReply();
            }
        }
        throw new ApiException(HttpStatus.NOT_FOUND, "Voting with the specified parameters does not exist.");
    }

    public GetVotingResponse getVoting(String votingId) {
        Voting voting = votingRepository.findById(Integer.valueOf(votingId)).orElseThrow(() ->
                new ApiException(HttpStatus.NOT_FOUND, "Voting with the specified parameters does not exist."));

        return calculateVotingResult(voting);
    }

    private GetVotingResponse calculateVotingResult(Voting voting) {
        VotingType votingType = voting.getVotingType();
        VotingResult votingResult;
        int numberOfMps;
        int numberOfYes = 0;
        int numberOfNo = 0;
        int numberOfAbstention = 0;

        numberOfMps = switch(votingType) {
            case PRESENCE -> voting.getVotes().size();
            case SIMPLE -> getNumberOfRepresentativesMPs();
            case QUALIFIED -> AppUtils.getNumberOfMPs();
        };
        for (Vote vote : voting.getVotes()) {
            switch (vote.getVoteReply()) {
                case YES:
                    numberOfYes++;
                    break;
                case NO:
                    numberOfNo++;
                    break;
                case ABSTENTION:
                    numberOfAbstention++;
            }
        }
        if (votingType.equals(VotingType.PRESENCE)) {
            votingResult = VotingResult.ACCEPTED;
        } else {
            votingResult = numberOfYes > (numberOfMps / 2) ? VotingResult.ACCEPTED : VotingResult.REJECTED;
        }

        return new GetVotingResponse(votingResult, numberOfMps, numberOfYes, numberOfNo, numberOfAbstention);
    }

    private int getNumberOfRepresentativesMPs() {
        Voting lastSimpleVoting = votingRepository.findLastPresenceVotingByDate().orElseThrow(() ->
                new ApiException(HttpStatus.NOT_FOUND, "No presence voting found."));
        return lastSimpleVoting.getVotes().size();
    }

    public GetVotingsByDayResponse getVotingsByDay(String date) {
        List<Voting> votings;
        if (!DateUtil.isValidDateFormat(date)) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid date format. Correct date format: yyyy-MM-dd");
        }
        votings = votingRepository.findAllByPeriod(DateUtil.getStartOfDay(date), DateUtil.getEndOfDay(date));

        return createGetVotingsByDayResponse(votings);
    }

    private GetVotingsByDayResponse createGetVotingsByDayResponse(List<Voting> votings) {
        GetVotingResponse getVotingResponse;
        List<VotingResp> votingResponses;
        List<VoteResp> voteResponses;

        votingResponses = new ArrayList<>();
        for (Voting voting : votings) {
            voteResponses = new ArrayList<>();
            for (Vote vote : voting.getVotes()) {
                voteResponses.add(new VoteResp(vote.getCodeOfMP(), vote.getVoteReply()));
            }
            getVotingResponse = calculateVotingResult(voting);
            votingResponses.add(new VotingResp(voting.getDate(), voting.getSubject(), voting.getVotingType(), voting.getPresident(),
                                getVotingResponse.getVotingResult(), getVotingResponse.getNumberOfMPs(), voteResponses));
        }
        return new GetVotingsByDayResponse(votingResponses);
    }

    public GetAverageVotesResponse getAverageVotes(LocalDateTime from, LocalDateTime till) {
        Map<String, Integer> numberOfVotesByMPs = new HashMap<>();
        Set<String> codeOfMPs = new HashSet<>();
        List<Voting> votings = votingRepository.findSimpleAndQualifiedByPeriod(from, till);
        int total = 0;
        double average = 0;

        for (Voting voting : votings) {
            for (Vote vote : voting.getVotes()) {
                if(numberOfVotesByMPs.containsKey(vote.getCodeOfMP())) {
                    numberOfVotesByMPs.put(vote.getCodeOfMP(), numberOfVotesByMPs.get(vote.getCodeOfMP()) + 1);
                } else {
                    numberOfVotesByMPs.put(vote.getCodeOfMP(), 1);
                }
            }
        }

        for (Map.Entry<String, Integer> set : numberOfVotesByMPs.entrySet()) {
            codeOfMPs.add(set.getKey());
            total += set.getValue();
        }

        if (!codeOfMPs.isEmpty()) {
            average = total / (double) codeOfMPs.size();
        }

        return new GetAverageVotesResponse(NumberUtil.trimDouble(average));
    }
}
