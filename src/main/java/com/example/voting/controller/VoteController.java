package com.example.voting.controller;

import com.example.voting.payload.getvote.GetVoteResponse;
import com.example.voting.payload.savevoting.SaveVotingRequest;
import com.example.voting.payload.savevoting.SaveVotingResponse;
import com.example.voting.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("szavazasok")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/szavazas")
    @ResponseBody
    public ResponseEntity<SaveVotingResponse> saveVoting(@Valid @RequestBody SaveVotingRequest request) {
        return new ResponseEntity<>(voteService.saveVoting(request), HttpStatus.OK);
    }

    @GetMapping("/szavazat")
    @ResponseBody
    public ResponseEntity<GetVoteResponse> getVote(@RequestParam("szavazas") String votingId, @RequestParam("kepviselo") String codeOfMP) {
        return new ResponseEntity<>(voteService.getVote(votingId, codeOfMP), HttpStatus.OK);
    }
}