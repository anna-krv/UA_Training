package ua.finalproject.periodicals.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.*;
import ua.finalproject.periodicals.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

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
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with username " + username))
                .getAccount();
    }

    public Optional<User> findByUsername(User user) {
        return userRepository.findByUsername(user.getUsername());
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
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

    public List<Periodical> findPeriodicalsByUsernameAndFilterAndSort(String username, String title,
                                                                      List<String> topicsSelected,
                                                                      String sort) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
                "Cannot find user with username " + username));
        title = title.toLowerCase();
        String finalTitle = title;
        return user.getSubscriptions()
                .stream()
                .map(Subscription::getPeriodical)
                .filter(periodical -> (periodical.getTitle().toLowerCase().contains(finalTitle)) &&
                        (topicsSelected == null || topicsSelected.contains(periodical.getTopic().toLowerCase(Locale.ROOT))))
                .sorted((p1, p2) -> sort.equals("title") ? p1.getTitle().compareTo(p2.getTitle()) :
                        p1.getPrice().compareTo(p2.getPrice()))
                .collect(Collectors.toList());
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

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(
                "Cannot find user with id " + id));
    }

    public User changeBlockStatus(Long id) throws UserNotFoundException {
        User user = findById(id);
        user.setAccountNonLocked(!user.isAccountNonLocked());
        return save(user);
    }

    public boolean checkCredentials(User user) throws UsernameNotFoundException {
        User userFound = findByUsername(user).orElseThrow(() -> new UsernameNotFoundException(
                "Cannot find user with username " + user.getUsername()));
        return userFound.getPassword().equals(user.getPassword());
    }
}
