package com.example.voting.controller;

import com.example.voting.payload.getaveragevotes.GetAverageVotesResponse;
import com.example.voting.payload.getvote.GetVoteResponse;
import com.example.voting.payload.getvoting.GetVotingResponse;
import com.example.voting.payload.getvotingsbyday.GetVotingsByDayResponse;
import com.example.voting.payload.savevoting.SaveVotingRequest;
import com.example.voting.payload.savevoting.SaveVotingResponse;
import com.example.voting.service.VoteService;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

    @GetMapping("/eredmeny")
    @ResponseBody
    public ResponseEntity<GetVotingResponse> getVoting(@RequestParam("szavazas") String votingId) {
        return new ResponseEntity<>(voteService.getVoting(votingId), HttpStatus.OK);
    }

    @GetMapping("/napi-szavazasok/{datum}")
    @ResponseBody
    public ResponseEntity<GetVotingsByDayResponse> getVotingsByDay(@PathVariable("datum") String date) {
        return new ResponseEntity<>(voteService.getVotingsByDay(date), HttpStatus.OK);
    }

    @GetMapping("/kepviselo-reszvetel-atlag")
    @ResponseBody
    public ResponseEntity<GetAverageVotesResponse> getAverageVotes(@RequestParam("tol") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                                                   @RequestParam("ig") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime till) {
        return new ResponseEntity<>(voteService.getAverageVotes(from, till), HttpStatus.OK);
    }
}