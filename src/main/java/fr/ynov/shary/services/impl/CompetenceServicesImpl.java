package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.Comptence;
import fr.ynov.shary.repository.CompetenceRepository;
import fr.ynov.shary.services.CompetenceService;

public class CompetenceServicesImpl implements CompetenceService {

    private CompetenceRepository  competenceRepository;
    @Override
    public long createCompetence(Comptence comptence) {
        Comptence savedComptence = competenceRepository.save(comptence);
        return  savedComptence.getCompt_id();
    }
    @Override
    public void deleteCompetence(long id) {
        competenceRepository.deleteById(id);
    }
    @Override
    public long updateCompetence(Comptence comptence) {
        Comptence updatedComptence = competenceRepository.save(comptence);
        return updatedComptence.getCompt_id();
    }
    @Override
    public Comptence getCompetence(long id) {
        return competenceRepository.findById(id).orElse(null);
    }
}
