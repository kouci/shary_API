package fr.ynov.shary.services;

import fr.ynov.shary.models.User;

public interface MatchUserServices {

    public long createMatchUser(User user);

    public void deleteMatchUser(long id);

    public long updateMatchUser(User user);

    public User getMatchUser(long id);

}
