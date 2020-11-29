package org.tms.finalproject.service.database;

import org.tms.finalproject.entity.User;

public interface UserService {
    User getUserByLogin(String login);
    void createUser(User user);
}
