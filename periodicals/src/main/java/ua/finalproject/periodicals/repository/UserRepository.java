package ua.finalproject.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.finalproject.periodicals.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findById(Long id);

    User save(User user);

    List<User> findAll();

    @Query(value = "SELECT * FROM user WHERE username<>?", nativeQuery = true)
    List<User> findAllExceptOne(String username);
}
