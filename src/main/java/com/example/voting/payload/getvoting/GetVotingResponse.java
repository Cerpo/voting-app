package com.example.voting.payload.getvoting;

import com.example.voting.model.vote.VotingResult;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

public class GetVotingResponse {
    @Getter
    @JsonProperty("eredmeny")
    private VotingResult votingResult;
    @Getter
    @JsonProperty("kepviselokSzama")
    private Integer numberOfMPs;
    @JsonProperty("igenekSzama")
    private Integer numberOfYes;
    @JsonProperty("nemekSzama")
    private Integer numberOfNo;
    @JsonProperty("tartozkodasokSzama")
    private Integer numberOfAbstention;

    private GetVotingResponse() {
    }

    public GetVotingResponse(VotingResult votingResult, Integer numberOfMPs, Integer numberOfYes, Integer numberOfNo, Integer numberOfAbstention) {
        this.votingResult = votingResult;
        this.numberOfMPs = numberOfMPs;
        this.numberOfYes = numberOfYes;
        this.numberOfNo = numberOfNo;
        this.numberOfAbstention = numberOfAbstention;
    }
}
