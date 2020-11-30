package org.tms.finalproject.service.database;

import org.tms.finalproject.entity.User;
import org.tms.finalproject.entity.order.Order;

public interface UserService {
    void createUser(User user);
    User getUserById(long id);
    User getUserByLogin(String login);

    User getActualUser();
    String getActualUserName();

    void recalculateAverageRatingForUserById(long userId);
}
