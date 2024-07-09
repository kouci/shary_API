package fr.ynov.shary.services;

import fr.ynov.shary.models.UserLog;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserLogService {

    public long createUserLog(UserLog userLog);

    public void deleteUserLog(long id);

    public long updateUserLog(UserLog userLog);

    public UserLog getUserLog(long id);

    public List<UserLog>  getAllUserLogs();
}
