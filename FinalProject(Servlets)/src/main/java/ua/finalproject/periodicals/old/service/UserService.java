package ua.finalproject.periodicals.old.service;

import ua.finalproject.periodicals.old.dao.DaoFactory;
import ua.finalproject.periodicals.old.dao.UserDao;
import ua.finalproject.periodicals.old.entity.Role;
import ua.finalproject.periodicals.old.entity.User;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {
    private final UserDao userDao = DaoFactory.getInstance().createUserDao();

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public Optional<User> findByCredentials(String username, String password) {
        return userDao.findByCredentials(username, password);
    }

    public void putMoney(Long userId, BigDecimal moneyToPut) throws SQLException {
            userDao.updateBalance(userId, moneyToPut);
    }

    public List<User> findByIdNot(Long userId, int number) {
        return userDao.findByIdNot(userId, number);
    }

    public Optional<User> changeBlockStatus(Long id) {
        return userDao.changeBlockStatus(id);
    }

    public void create(User user) throws SQLException {
        userDao.create(setUp(user));
    }
    private User setUp(User user) {
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        user.setBalance(BigDecimal.ZERO);
        user.setAuthority(Role.USER);
        return user;
    }
}
