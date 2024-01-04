package com.example.voting.payload.getaveragevotes;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAverageVotesResponse {
    @JsonProperty("atlag")
    private Double average;

    private GetAverageVotesResponse() {
    }

    public GetAverageVotesResponse(Double average) {
        this.average = average;
    }
}
