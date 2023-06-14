package com.practive.jpa.repository;

import com.practive.jpa.entity.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    @EntityGraph(attributePaths = "members")
    Team findByTeamName(String teamName);
}
