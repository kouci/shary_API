package fr.ynov.shary.services.impl;

import fr.ynov.shary.models.Category;
import fr.ynov.shary.models.UserLog;
import fr.ynov.shary.repository.UserLogRepository;
import fr.ynov.shary.services.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLogServiceImpl implements UserLogService {


    @Autowired
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

    @Override
    public List<UserLog> getAllUserLogs() {
        return userLogRepository.findAll();
    }
}
