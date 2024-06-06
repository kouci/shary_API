package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.UserLog;
import fr.ynov.shary.repository.UserLogRepository;
import fr.ynov.shary.services.UserLogService;

public class UserLogServiceImpl implements UserLogService {


    private UserLogRepository userLogRepository;
    @Override
    public long createUserLog(UserLog userLog) {
        UserLog savedUserLog = userLogRepository.save(userLog);
        return savedUserLog.getUserLog_id();
    }

    @Override
    public void deleteUserLog(long id) {
        userLogRepository.deleteById(id);
    }

    @Override
    public long updateUserLog(UserLog userLog) {
        UserLog updatedUserLog = userLogRepository.save(userLog);
        return  updatedUserLog.getUserLog_id();
    }

    @Override
    public UserLog getUserLog(long id) {
        return  userLogRepository.findById(id).orElse(null);
    }
}
