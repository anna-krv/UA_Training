package ua.finalproject.periodicals.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByLogin(User user) {
        return userRepository.findByLogin(user.getLogin());
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
