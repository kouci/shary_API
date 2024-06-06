package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.MatchUser;
import fr.ynov.shary.repository.MatchUserRepository;
import fr.ynov.shary.services.MatchUserService;

public class MatchUserServiceImpl  implements MatchUserService {

    private MatchUserRepository matchUserRepository;
    @Override
    public long createMatchUser(MatchUser matchUser) {
        MatchUser savedMatchUser = matchUserRepository.save(matchUser);
        return savedMatchUser.getMatch_id();
    }

    @Override
    public void deleteMatchUser(long id) {
       matchUserRepository.deleteById(id);
    }

    @Override
    public long updateMatchUser(MatchUser matchUser) {
        MatchUser updatedMatchUser = matchUserRepository.save(matchUser);
        return updatedMatchUser.getMatch_id();
    }

    @Override
    public MatchUser getMatchUser(long id) {
        return matchUserRepository.findById(id).orElse(null);
    }
}
