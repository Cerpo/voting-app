package com.example.voting.service;

import com.example.voting.payload.savevoting.SaveVotingRequest;
import com.example.voting.payload.savevoting.SaveVotingResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VoteService {
    public SaveVotingResponse saveVoting(SaveVotingRequest request) {
        return new SaveVotingResponse("-1");
    }
}
