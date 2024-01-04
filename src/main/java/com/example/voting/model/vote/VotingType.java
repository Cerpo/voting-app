package com.example.voting.model.vote;

import com.example.voting.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum VotingType {
    PRESENCE("j"),
    SIMPLE("e"),
    QUALIFIED("m");

    private final String value;

    VotingType(String value) {
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

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static VotingType fromString(String value) {
        for(VotingType t : VotingType.values()){
            if(t.getValue().equals(value)){
                return t;
            }
        }
        throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid voting type.");
    }
}
