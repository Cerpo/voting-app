package com.example.voting.model.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Integer> {
    Optional<Voting> findByDate(LocalDateTime date);

    @Query("SELECT v FROM Voting v WHERE v.votingType = 0 ORDER BY v.date DESC LIMIT 1")
    Optional<Voting> findLastPresenceVotingByDate();
}
