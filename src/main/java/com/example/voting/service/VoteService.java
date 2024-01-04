package com.example.voting.service;

import com.example.voting.exception.ApiException;
import com.example.voting.model.vote.Vote;
import com.example.voting.model.vote.Voting;
import com.example.voting.model.vote.VotingRepository;
import com.example.voting.payload.savevoting.SaveVotingRequest;
import com.example.voting.payload.savevoting.SaveVotingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

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
}
