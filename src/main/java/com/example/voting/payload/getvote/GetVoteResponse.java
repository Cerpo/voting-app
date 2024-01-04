package com.example.voting.payload.getvote;

import com.example.voting.model.vote.VoteReply;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetVoteResponse {
    @JsonProperty("szavazat")
    private VoteReply voteReply;

    private GetVoteResponse() {
    }

    public GetVoteResponse(VoteReply voteReply) {
        this.voteReply = voteReply;
    }
}
