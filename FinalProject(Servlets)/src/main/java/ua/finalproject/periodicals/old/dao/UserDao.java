package ua.finalproject.periodicals.old.dao;

import ua.finalproject.periodicals.old.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User, Long>{
    Optional<User> findByCredentials(String username, String password);

    boolean updateBalance(Long userId, BigDecimal moneyToPut);

    List<User> findByIdNot(Long userId, int number);

    Optional<User> changeBlockStatus(Long id);
}
