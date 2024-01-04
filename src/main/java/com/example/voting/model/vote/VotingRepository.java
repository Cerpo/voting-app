package com.example.voting.model.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Integer> {
    Optional<Voting> findByDate(LocalDateTime date);
}
