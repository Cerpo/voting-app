package com.example.voting.model.vote;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
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
}
