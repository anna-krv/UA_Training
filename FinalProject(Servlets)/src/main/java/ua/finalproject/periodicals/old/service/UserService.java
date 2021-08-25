package ua.finalproject.periodicals.old.service;

import ua.finalproject.periodicals.old.dao.DaoFactory;
import ua.finalproject.periodicals.old.dao.UserDao;
import ua.finalproject.periodicals.old.entity.User;

import java.math.BigDecimal;
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

    public boolean putMoney(Long userId, BigDecimal moneyToPut) {
            return userDao.updateBalance(userId, moneyToPut);
    }
}
