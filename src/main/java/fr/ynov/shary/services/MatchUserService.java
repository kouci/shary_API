package fr.ynov.shary.services;

import fr.ynov.shary.models.MatchUser;

public interface MatchUserService {

    public long createMatchUser(MatchUser matchUser);

    public void deleteMatchUser(long id);

    public long updateMatchUser(MatchUser matchUser);

    public MatchUser getMatchUser(long id);

}
