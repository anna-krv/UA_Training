package ua.finalproject.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.finalproject.periodicals.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account save(Account account);
}