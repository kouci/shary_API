package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.Competence;
import fr.ynov.shary.repository.CompetenceRepository;
import fr.ynov.shary.services.CompetenceService;

public class CompetenceServicesImpl implements CompetenceService {

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
}
