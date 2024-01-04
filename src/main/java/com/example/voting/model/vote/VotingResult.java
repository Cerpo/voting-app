package com.example.voting.model.vote;

import com.fasterxml.jackson.annotation.JsonValue;

public enum VotingResult {
    ACCEPTED("F"),
    REJECTED("U");

    private final String value;

    VotingResult(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }
}
