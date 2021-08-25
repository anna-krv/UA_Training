package ua.finalproject.periodicals.old.dao;

import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.Criteria;

import java.util.List;

public interface PeriodicalDao extends GenericDao<Periodical, Long>{
    List<String> findTopics();

    List<Periodical> findByCriteria(Criteria criteria);

    void deleteByTitle(String title);

    List<String> findAllTopicsByUser(User user);
}

