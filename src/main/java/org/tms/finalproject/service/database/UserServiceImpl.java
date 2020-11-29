package org.tms.finalproject.service.database;

import org.springframework.stereotype.Service;
import org.tms.finalproject.entity.User;
import org.tms.finalproject.repository.UserRepository;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByLogin(String login) {

        Optional<User> user = userRepository.findByUserName(login);
        return user.orElseThrow(RuntimeException :: new);
    }

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null!");
        }
        userRepository.save(user);
    }
}
