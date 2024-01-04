package com.example.voting.payload.savevoting;

import com.example.voting.model.vote.VotingType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class SaveVotingRequest {
    @NotNull
    @JsonProperty("idopont")
    private LocalDateTime date;
    @NotBlank
    @JsonProperty("targy")
    private String subject;
    @NotNull
    @JsonProperty("tipus")
    private VotingType votingType;
    @NotBlank
    @JsonProperty("elnok")
    private String president;
    @Valid
    @NotEmpty
    @JsonProperty("szavazatok")
    private List<VoteRequest> votes;
}
