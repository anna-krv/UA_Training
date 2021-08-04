package ua.finalproject.periodicals.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.Account;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(
                () -> new UsernameNotFoundException("bad credentials")).getAccount();
    }

    public Optional<User> findByUsername(User user) {

        return userRepository.findByUsername(user.getUsername());
    }


    public User save(User user) {
        return userRepository.save(setUp(user));
    }

    private User setUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Account account = Account.builder().user(user).build();
        user.setAccount(account);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("bad credentials"));

    }
}
