package com.example.voting.model.vote;

import com.example.voting.exception.ApiException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.http.HttpStatus;

public enum VoteReply {
    YES("i"),
    NO("n"),
    ABSTENTION("t");

    private final String value;

    VoteReply(String value) {
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
    public static VoteReply fromString(String value) {
        for(VoteReply t : VoteReply.values()){
            if(t.getValue().equals(value)){
                return t;
            }
        }
        throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid vote reply.");
    }
}
