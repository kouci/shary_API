package fr.ynov.shary.services;

import fr.ynov.shary.models.Competence;

public interface CompetenceService {
    public long createCompetence(Competence competence);

    public void deleteCompetence(long id);

    public long updateCompetence(Competence competence);

    public Competence getCompetence(long id);
}
