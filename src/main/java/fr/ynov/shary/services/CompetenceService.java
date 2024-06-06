package fr.ynov.shary.services;

import fr.ynov.shary.models.Comptence;

public interface CompetenceService {
    public long createCompetence(Comptence comptence);

    public void deleteCompetence(long id);

    public long updateCompetence(Comptence comptence);

    public Comptence getCompetence(long id);
}
