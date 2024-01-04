package com.example.voting.payload.getvotingsbyday;

import com.example.voting.model.vote.VoteReply;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteResp {
    @JsonProperty("kepviselo")
    private String codeOfMP;
    @JsonProperty("szavazat")
    private VoteReply voteReply;

    private VoteResp() {
    }

    public VoteResp(String codeOfMP, VoteReply voteReply) {
        this.codeOfMP = codeOfMP;
        this.voteReply = voteReply;
    }
}
