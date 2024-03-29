package com.example.voting.model.vote;

import com.example.voting.payload.savevoting.SaveVotingRequest;
import com.example.voting.payload.savevoting.VoteRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "voting")
public class Voting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voting_id")
    private Integer votingId;

    @Column(name = "voting_date")
    private LocalDateTime date;

    @Column(name = "subject")
    private String subject;

    @Column(name = "voting_type")
    private VotingType votingType;

    @Column(name = "president")
    private String president;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "voting_fk", referencedColumnName = "voting_id")
    private List<Vote> votes;

    public static Voting convertRequest(SaveVotingRequest request) {
        Voting voting = new Voting();

        voting.setDate(request.getDate());
        voting.setSubject(request.getSubject());
        voting.setVotingType(request.getVotingType());
        voting.setPresident(request.getPresident());
        voting.votes = new ArrayList<>();
        for (VoteRequest vote : request.getVotes()) {
            voting.votes.add(new Vote(vote.getCodeOfMP(), vote.getVoteReply()));
        }

        return voting;
    }
}
