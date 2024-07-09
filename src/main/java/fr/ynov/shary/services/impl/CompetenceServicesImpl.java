package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.Competence;
import fr.ynov.shary.repository.CompetenceRepository;
import fr.ynov.shary.services.CompetenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetenceServicesImpl implements CompetenceService {

    @Autowired
    private CompetenceRepository  competenceRepository;
    @Override
    public long createCompetence(Competence competence) {
        Competence savedCompetence = competenceRepository.save(competence);
        return  savedCompetence.getComptence_id();
    }
    @Override
    public void deleteCompetence(long id) {
        competenceRepository.deleteById(id);
    }
    @Override
    public long updateCompetence(Competence competence) {
        Competence updatedCompetence = competenceRepository.save(competence);
        return updatedCompetence.getComptence_id();
    }
    @Override
    public Competence getCompetence(long id) {
        return competenceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Competence> getAll() {
        return competenceRepository.findAll();
    }
}
