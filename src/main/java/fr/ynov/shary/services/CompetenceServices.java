package fr.ynov.shary.services;

import fr.ynov.shary.models.User;

public interface CompetenceServices {
    public long createCompetence(User user);

    public void deleteCompetence(long id);

    public long updateCompetence(User user);

    public User getCompetence(long id);
}
