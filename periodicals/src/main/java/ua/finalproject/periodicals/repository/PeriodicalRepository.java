package ua.finalproject.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.finalproject.periodicals.entity.Periodical;

import java.util.List;

@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, Long> {

    List<Periodical> findAllByOrderByTitleAsc();

    List<Periodical> findAllByOrderByPriceAsc();

    @Query(value = "SELECT DISTINCT topic FROM periodical", nativeQuery = true)
    List<String> findTopics();

    List<Periodical> findByTopicInIgnoreCaseOrderByTitleAsc(List<String> topics);

    List<Periodical> findByTopicInIgnoreCaseOrderByPriceAsc(List<String> topics);

    List<Periodical> findByTitleContainsIgnoreCase(String trim);
}
