package ua.finalproject.periodicals.old.dao;

import ua.finalproject.periodicals.old.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract PeriodicalDao createPeriodicalDao();
    public abstract SubscriptionDao createSubscriptionDao();

    public static DaoFactory getInstance(){
        if (daoFactory==null){
            synchronized(DaoFactory.class){
                if (daoFactory==null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}


