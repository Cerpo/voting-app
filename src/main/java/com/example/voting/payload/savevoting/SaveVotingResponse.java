package com.example.voting.payload.savevoting;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveVotingResponse {
    @JsonProperty("szavazasId")
    private String votingId;

    private SaveVotingResponse() {
    }

    public SaveVotingResponse(String votingId) {
        this.votingId = votingId;
    }
}
