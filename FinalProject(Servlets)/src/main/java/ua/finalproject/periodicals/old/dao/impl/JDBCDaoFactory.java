package ua.finalproject.periodicals.old.dao.impl;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;
import ua.finalproject.periodicals.old.dao.DaoFactory;
import ua.finalproject.periodicals.old.dao.PeriodicalDao;
import ua.finalproject.periodicals.old.dao.SubscriptionDao;
import ua.finalproject.periodicals.old.dao.UserDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public PeriodicalDao createPeriodicalDao() {
        return new JDBCPeriodicalDao(getConnection());
    }

    @Override
    public SubscriptionDao createSubscriptionDao() {
        return new JDBCSubscriptionDao(getConnection());
    }

    private Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                    Configurations.getProperty(Constants.DB_URL),
                    Configurations.getProperty(Constants.DB_USER),
                    Configurations.getProperty(Constants.DB_PASSWORD)
            );
        } catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}
