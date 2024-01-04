package com.example.voting.payload.getvotingsbyday;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GetVotingsByDayResponse {
    @JsonProperty("szavazasok")
    private List<VotingResp> votings;

    private GetVotingsByDayResponse() {
    }

    public GetVotingsByDayResponse(List<VotingResp> votings) {
        this.votings = votings;
    }
}
