package org.tms.finalproject.service.database;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tms.finalproject.entity.Comment;
import org.tms.finalproject.entity.User;
import org.tms.finalproject.entity.order.Order;
import org.tms.finalproject.repository.UserRepository;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null!");
        }
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("UserId is not correct!");
        }
        userRepository.deleteById(userId);
    }

    @Override
    public User getUserById(long userId) {
        if (userId < 1) {
            throw new IllegalArgumentException("UserId is not correct!");
        }
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(RuntimeException :: new);
    }

    @Override
    public User getUserByLogin(String login) {
        if (login == null) {
            throw new IllegalArgumentException("UserName is null!");
        }
        Optional<User> user = userRepository.findByUserName(login);
        return user.orElseThrow(RuntimeException :: new);
    }

    @Override
    public User getActualUser() {
        String userName = getActualUserName();
        return getUserByLogin(userName);
    }

    @Override
    public String getActualUserName() {
        SecurityContext context = SecurityContextHolder.getContext();
        return  ((org.springframework.security.core.userdetails.User) context.getAuthentication().getPrincipal()).getUsername();
    }

    @Override
    public void recalculateAverageRatingForUserById(long userId) {
        // Почему-то юзер приходит с не совсем актуальным списком коментов, последнего нет. Разобраться.
        User user = getUserById(userId);
        Double ratingSum = user.getComments()
                .stream()
                .map(Comment::getRating)
                .reduce(Double::sum)
                .orElse(0.0);
        user.setRating(ratingSum / user.getComments().size());
        userRepository.save(user);
    }
}
