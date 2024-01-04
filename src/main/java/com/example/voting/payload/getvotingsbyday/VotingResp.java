package com.example.voting.payload.getvotingsbyday;

import com.example.voting.model.vote.VotingResult;
import com.example.voting.model.vote.VotingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.List;

public class VotingResp {
    @JsonProperty("idopont")
    private LocalDateTime date;
    @JsonProperty("targy")
    private String subject;
    @JsonProperty("tipus")
    private VotingType votingType;
    @JsonProperty("elnok")
    private String president;
    @JsonProperty("eredmeny")
    private VotingResult votingResult;
    @JsonProperty("kepviselokSzama")
    private Integer numberOfMPs;
    @JsonProperty("szavazatok")
    private List<VoteResp> votes;

    private VotingResp() {
    }

    public VotingResp(LocalDateTime date, String subject, VotingType votingType, String president, VotingResult votingResult, Integer numberOfMPs, List<VoteResp> votes) {
        this.date = date;
        this.subject = subject;
        this.votingType = votingType;
        this.president = president;
        this.votingResult = votingResult;
        this.numberOfMPs = numberOfMPs;
        this.votes = votes;
    }
}
