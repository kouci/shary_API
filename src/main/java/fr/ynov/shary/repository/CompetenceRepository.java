package fr.ynov.shary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.ynov.shary.models.Competence;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenceRepository extends JpaRepository<Competence, Long> {

}
