package ua.finalproject.periodicals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.finalproject.periodicals.entity.Periodical;

import java.util.List;

@Repository
public interface PeriodicalRepository extends JpaRepository<Periodical, Long> {
    Periodical save(Periodical periodical);

    List<Periodical> findAll();

    List<Periodical> findAllByOrderByTitleAsc();

    List<Periodical> findAllByOrderByPriceAsc();

    List<Periodical> findByTitleIgnoreCase(String title);
}
