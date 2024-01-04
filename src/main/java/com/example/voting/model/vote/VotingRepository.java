package com.example.voting.model.vote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VotingRepository extends JpaRepository<Voting, Integer> {
    Optional<Voting> findByDate(LocalDateTime date);

    @Query("SELECT v FROM Voting v WHERE v.votingType = 0 ORDER BY v.date DESC LIMIT 1")
    Optional<Voting> findLastPresenceVotingByDate();

    @Query("SELECT v FROM Voting v WHERE (v.date BETWEEN :fromDate AND :tillDate)")
    List<Voting> findAllByPeriod(@Param("fromDate") LocalDateTime from, @Param("tillDate") LocalDateTime till);

    @Query("SELECT v FROM Voting v WHERE (v.date BETWEEN :fromDate AND :tillDate) AND (v.votingType = 1 or v.votingType = 2)")
    List<Voting> findSimpleAndQualifiedByPeriod(@Param("fromDate") LocalDateTime from, @Param("tillDate") LocalDateTime till);
}
