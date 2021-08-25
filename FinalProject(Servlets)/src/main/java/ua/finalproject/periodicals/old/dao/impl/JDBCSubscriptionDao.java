package ua.finalproject.periodicals.old.dao.impl;

import ua.finalproject.periodicals.old.dao.MoneyAccountException;
import ua.finalproject.periodicals.old.dao.SubscriptionDao;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.Subscription;
import ua.finalproject.periodicals.old.entity.SubscriptionKey;
import ua.finalproject.periodicals.old.entity.User;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class JDBCSubscriptionDao implements SubscriptionDao {
    private static final String QUERY_CHARGE_MONEY = "UPDATE user SET balance = balance-? WHERE id=?";
    private static final String QUERY_DELETE = "DELETE FROM subscription WHERE periodical_id=? AND user_id=?";
    private static final String  QUERY_DELETE_BY_PERIODICAL="DELETE FROM subscription WHERE periodical_id=?";
    private static final String QUERY_INSERT = "INSERT INTO subscription(periodical_id, user_id, last_payment_date_time," +
            "next_payment_date_time, payment_period_in_days, status) VALUES (?,?,?,?,?,?)";

    private static final String QUERY_FIND= "SELECT * FROM subscription WHERE user_id=? AND periodical_id=?";
    private final static Logger logger = Logger.getLogger(JDBCSubscriptionDao.class.getName());
    private Connection connection;

    public JDBCSubscriptionDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Subscription entity) throws SQLException{
        try (PreparedStatement psForInsert = connection.prepareStatement(QUERY_INSERT);
             PreparedStatement psForCharge = connection.prepareStatement(QUERY_CHARGE_MONEY)) {
            prepareStatementForEntity(psForInsert, entity);
            prepareStatementForCharge(psForCharge, entity);

            connection.setAutoCommit(false);
            psForInsert.execute();
            psForCharge.execute();
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            logger.severe(ex.getMessage());
            throw new MoneyAccountException();
        } finally {
            connection.setAutoCommit(true);
        }
    }

    private void prepareStatementForEntity(PreparedStatement preparedStatement,
                                           Subscription entity) throws SQLException {

        preparedStatement.setLong(1, entity.getPeriodical().getId());
        preparedStatement.setLong(2, entity.getUser().getId());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(entity.getLastPaymentDateTime()));
        preparedStatement.setTimestamp(4, Timestamp.valueOf(entity.getNextPaymentDateTime()));
        preparedStatement.setInt(5, entity.getPaymentPeriodInDays());
        preparedStatement.setBoolean(6, entity.isStatus());
    }


    private void prepareStatementForCharge(PreparedStatement preparedStatement,
                                           Subscription entity) throws SQLException {
        preparedStatement.setBigDecimal(1, entity.getPeriodical().getPrice());
        preparedStatement.setLong(2, entity.getUser().getId());
    }

    @Override
    public Optional<Subscription> findById(SubscriptionKey id) {
        return Optional.ofNullable(null);
    }

    @Override
    public List<Subscription> findAll() {
        return null;
    }

    @Override
    public void update(Subscription entity) {

    }
    @Override
    public Optional<Subscription> findByUserAndPeriodical(User user, Periodical periodical) {
        Optional<Subscription> optional = Optional.empty();
        try(PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND))
        {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setLong(2, periodical.getId());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                optional=Optional.of(extractFromResultSet(rs));
            }
        }
        catch(SQLException ex){
            logger.severe(ex.getMessage());
        }
        return optional;
    }
    @Override
    public void delete(SubscriptionKey id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE)) {
            preparedStatement.setLong(1, id.getPeriodicalId());
            preparedStatement.setLong(2, id.getUserId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
    }
    @Override
    public void deleteByPeriodicalId(Long periodicalId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE_BY_PERIODICAL)) {
            preparedStatement.setLong(1, periodicalId);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            logger.severe(ex.getMessage());
        }
    }

    private Subscription extractFromResultSet(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionKey(new SubscriptionKey(rs.getLong("periodical_id"), rs.getLong("user_id")));
        subscription.setLastPaymentDateTime(rs.getTimestamp("last_payment_date_time").toLocalDateTime());
        subscription.setNextPaymentDateTime(rs.getTimestamp("next_payment_date_time").toLocalDateTime());
        return subscription;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}

