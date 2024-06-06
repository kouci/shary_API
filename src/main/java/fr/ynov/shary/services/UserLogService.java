package fr.ynov.shary.services;

import fr.ynov.shary.models.UserLog;

public interface UserLogService {

    public long createUserLog(UserLog userLog);

    public void deleteUserLog(long id);

    public long updateUserLog(UserLog userLog);

    public UserLog getUserLog(long id);
}
