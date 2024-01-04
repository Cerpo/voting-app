package com.example.voting.payload.savevoting;

import com.example.voting.model.vote.VoteReply;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VoteRequest {
    @NotBlank
    @JsonProperty("kepviselo")
    private String codeOfMP;
    @NotNull
    @JsonProperty("szavazat")
    private VoteReply voteReply;
}
