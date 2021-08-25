package ua.finalproject.periodicals.old.service;

import ua.finalproject.periodicals.old.dao.DaoFactory;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.dao.PeriodicalDao;
import ua.finalproject.periodicals.old.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PeriodicalService {
    private PeriodicalDao periodicalDao= DaoFactory.getInstance().createPeriodicalDao();
    public List<Periodical> findAll(){
        return periodicalDao.findAll();
    }
    public List<String> findTopics(){
        return periodicalDao.findTopics();
    }
    public List<Periodical> findByCriteria(Criteria criteria){
        return periodicalDao.findByCriteria(criteria);
    }
    public void update(Long id, Periodical periodical) throws SQLException {
        periodical.setId(id);
        periodicalDao.update(periodical);
    }

    public void create(Periodical entity) throws SQLException {
        periodicalDao.create(entity);
    }
    public Optional<Periodical> findById(Long id){
        return periodicalDao.findById(id);
    }
    public void delete(Long id){
        periodicalDao.delete(id);
    }
    public void deleteByTitle(String title){
        periodicalDao.deleteByTitle(title);
    }

    public List<String> findAllTopicsByUser(User user) {
        return periodicalDao.findAllTopicsByUser(user);
    }
}
