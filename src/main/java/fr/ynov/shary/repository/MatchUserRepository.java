package fr.ynov.shary.repository;

import fr.ynov.shary.models.MatchUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchUserRepository extends JpaRepository<MatchUser,Long> {
}
