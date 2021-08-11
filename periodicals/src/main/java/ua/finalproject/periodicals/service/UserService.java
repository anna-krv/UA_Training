package ua.finalproject.periodicals.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.Account;
import ua.finalproject.periodicals.entity.Role;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.exception.UserNotFoundException;
import ua.finalproject.periodicals.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("no user with username " + username));
    }

    public Optional<User> findByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Account findAccountByUsername(String username) throws UsernameNotFoundException {
        return getByUsername(username)
                .getAccount();
    }


    public User create(User user) {
        return userRepository.save(setUp(user));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    private User setUp(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Account account = Account.builder().user(user).balance(BigDecimal.ZERO).build();
        user.setAccount(account);
        user.setAuthority(Role.USER);
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("bad credentials"));
        user.setAuthorities(Arrays.asList(user.getAuthority()));
        return user;
    }


    public List<String> findAllTopicsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "Cannot find user with username " + username));
        return user.getSubscriptions()
                .stream()
                .map(subscription -> subscription.getPeriodical().getTopic())
                .distinct()
                .collect(Collectors.toList());
    }

    public List<User> findByUsernameNot(String username) {
        return userRepository.findByUsernameNot(username);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public User changeBlockStatus(Long id) throws UserNotFoundException {
        User user = getById(id);
        user.setAccountNonLocked(!user.isAccountNonLocked());
        return save(user);
    }

    public boolean checkCredentials(User user) throws UsernameNotFoundException {
        User userFound = findByUsername(user).orElseThrow(() -> new UsernameNotFoundException(
                "Cannot find user with username " + user.getUsername()));
        return userFound.getPassword().equals(user.getPassword());
    }


}
