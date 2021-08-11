package ua.finalproject.periodicals.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.finalproject.periodicals.entity.Periodical;

import java.util.List;

@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, Long> {
    @Query(value = "SELECT DISTINCT topic FROM periodical", nativeQuery = true)
    List<String> findTopics();

    @Override
    Page<Periodical> findAll(Pageable pageable);

    Page<Periodical> findByTitleContainsIgnoreCase(String trim, Pageable pageable);

    Page<Periodical> findAllByTopicInIgnoreCase(List<String> topicsSelected, Pageable pageable);

    @Query(value = "SELECT * "
            + "FROM periodical p inner join subscription s on p.id = s.periodical_id "
            + "WHERE s.user_id=?1 AND p.title LIKE %?2% ",
            countQuery = "SELECT count(*) "
                    + "FROM periodical p inner join subscription s on p.id = s.periodical_id "
                    + "WHERE s.user_id=?1 AND p.title LIKE '%?2%' ",
            nativeQuery = true)
    Page<Periodical> findByUserIdAndTitleContainsIgnoreCase(Long id, String title, Pageable pageable);

    @Query(value = "SELECT * "
            + "FROM periodical p inner join subscription s on p.id = s.periodical_id "
            + "WHERE s.user_id=?1 AND p.topic in ?2 ",
            countQuery = "SELECT count(*) "
                    + "FROM periodical p inner join subscription s on p.id = s.periodical_id "
                    + "WHERE s.user_id=?1 AND p.topic in ?2 ",
            nativeQuery = true)
    Page<Periodical> findByUserIdAndTopicInIgnoreCase(Long id, List<String> topicsSelected, Pageable pageable);

    @Query(value = "SELECT * "
            + "FROM periodical p inner join subscription s on p.id = s.periodical_id "
            + "WHERE s.user_id=?1 ",
            countQuery = "SELECT count(*) "
                    + "FROM periodical p inner join subscription s on p.id = s.periodical_id "
                    + "WHERE s.user_id=?1",
            nativeQuery = true)
    Page<Periodical> findByUserId(Long id, Pageable pageable);
}
