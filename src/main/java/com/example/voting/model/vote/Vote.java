package com.example.voting.model.vote;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_ id")
    private Integer voteId;

    @Column(name = "member_of_parlament")
    private String codeOfMP;

    @Column(name = "vote_reply")
    private VoteReply voteReply;

    public Vote(String codeOfMP, VoteReply voteReply) {
        this.codeOfMP = codeOfMP;
        this.voteReply = voteReply;
    }
}
