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

import java.util.*;
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
        Optional<User> user = userRepository.findByUsername(username);
        return user.orElseThrow(
                () -> new UsernameNotFoundException("bad credentials")).getAccount();
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
        Account account = Account.builder().user(user).build();
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
                                                                      String sort) {
        User user = userRepository.findByUsername(username).get();
        return user.getSubscriptions()
                .stream()
                .map(Subscription::getPeriodical)
                .filter(periodical -> (title == null || title.equals("any") || title.equalsIgnoreCase(periodical.getTitle())) &&
                        (topicsSelected == null || topicsSelected.contains(periodical.getTopic().toLowerCase(Locale.ROOT))))
                .sorted((p1, p2) -> sort.equals("title") ? p1.getTitle().compareTo(p2.getTitle()) :
                        (int) (p1.getPrice() - p2.getPrice()))
                .collect(Collectors.toList());
    }

    public List<String> findAllTopicsByUsername(String username) {
        User user = userRepository.findByUsername(username).get();
        return new ArrayList<>(user.getSubscriptions()
                .stream()
                .map(subscription -> subscription.getPeriodical().getTopic())
                .collect(Collectors.toSet()));
    }

    public List<User> findByUsernameNot(String username) {
        return userRepository.findByUsernameNot(username);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User changeBlockStatus(Long id) {
        User user = findById(id).get();
        user.setAccountNonLocked(!user.isAccountNonLocked());
        return save(user);
    }

    public boolean checkIfValid(User user) {
        Optional<User> userFound = findByUsername(user);
        return userFound.isPresent() && userFound.get().getPassword().equals(user.getPassword());
    }
}
