package ua.finalproject.periodicals.old.dao.impl;


import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;
import ua.finalproject.periodicals.old.dao.BCrypt;
import ua.finalproject.periodicals.old.dao.UserDao;
import ua.finalproject.periodicals.old.entity.Role;
import ua.finalproject.periodicals.old.entity.User;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class JDBCUserDao implements UserDao {
    private static final String QUERY_FIND_ALL = "SELECT * FROM user";
    private static final String QUERY_FIND_BY_ID_NOT = "SELECT * FROM user WHERE id<>? " +
            "ORDER BY name LIMIT ?, ?";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM user WHERE id=?";
    private static final String QUERY_FIND_BY_USERNAME = "SELECT * FROM user WHERE username=?";
    private static final String QUERY_UPDATE_ACCOUNT_NON_LOCKED=
            "UPDATE user SET account_non_locked = NOT account_non_locked WHERE id=?";
    private static final String QUERY_INSERT = "INSERT INTO user() VALUES ()";
    private static final String QUERY_UPDATE_BALANCE="UPDATE user SET balance=balance+? WHERE id=?";
    private static final Logger logger = Logger.getLogger(JDBCUserDao.class.getName());
    private static final int PAGE_SIZE = Integer.valueOf(Configurations.getProperty(Constants.PAGE_SIZE));

    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {

    }
    @Override
    public Optional<User> changeBlockStatus(Long id) {
        Optional<User> optional = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_ACCOUNT_NON_LOCKED)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            optional=findById(id);
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return optional;
    }
    @Override
    public Optional<User> findById(Long id) {
        Optional<User> optional = Optional.ofNullable(null);
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                optional = Optional.of(extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return optional;
    }

    @Override
    public List<User> findAll() {
        List<User> items = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(QUERY_FIND_ALL);
            while (rs.next()) {
                items.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<User> findByIdNot(Long userId, int number) {
        List<User> items = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_ID_NOT)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, PAGE_SIZE * number);
            preparedStatement.setInt(3, PAGE_SIZE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                items.add(extractFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }


    private User extractFromResultSet(ResultSet rs) throws SQLException {
        User entity = new User();

        entity.setId(rs.getLong("id"));
        entity.setName(rs.getString("name"));
        entity.setSurname(rs.getString("surname"));
        entity.setEmail(rs.getString("email"));
        entity.setUsername(rs.getString("username"));
        entity.setPassword(rs.getString("password"));
        entity.setLanguage(rs.getString("language"));
        entity.setAccountNonLocked(rs.getBoolean("account_non_locked"));
        entity.setAccountNonExpired(rs.getBoolean("account_non_expired"));
        entity.setCredentialsNonExpired(rs.getBoolean("credentials_non_expired"));
        entity.setEnabled(rs.getBoolean("enabled"));
        entity.setAuthority(Role.values()[rs.getInt("authority")]);
        entity.setBalance(rs.getInt("balance"));

        return entity;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    @Override
    public Optional<User> findByCredentials(String username, String password) {
        Optional<User> userFound = findByUsername(username);
        return userFound.isPresent() && BCrypt.checkpw(password, userFound.get().getPassword())
                ? userFound : Optional.empty();
    }

    @Override
    public boolean updateBalance(Long userId, BigDecimal moneyToPut) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(QUERY_UPDATE_BALANCE)){
            preparedStatement.setBigDecimal(1, moneyToPut);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            return true;
        }catch(SQLException ex){
            logger.severe(ex.getMessage());
            return false;
        }
    }

    private Optional<User> findByUsername(String username) {
        Optional<User> optional = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_BY_USERNAME)) {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                optional = Optional.of(extractFromResultSet(rs));
            }
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
        return optional;
    }
}
