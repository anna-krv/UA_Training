package ua.finalproject.periodicals.service;

import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.repository.PeriodicalRepository;

import java.util.List;

@Service
public class PeriodicalService {
    private final PeriodicalRepository periodicalRepository;

    public PeriodicalService(PeriodicalRepository periodicalRepository) {
        this.periodicalRepository = periodicalRepository;
    }

    public List<Periodical> find(String title, String sortBy) {
        if (title != null && !title.isEmpty()) {
            return findByTitle(title);
        }
        return findAll(sortBy);
    }

    public List<Periodical> findAll(String sortBy) {
        if (sortBy == null) {
            return findAll();
        }
        switch (sortBy) {
            case "title":
                return periodicalRepository.findAllByOrderByTitleAsc();
            case "price":
                return periodicalRepository.findAllByOrderByPriceAsc();
        }
        return periodicalRepository.findAll();
    }

    public List<Periodical> findAll() {
        return periodicalRepository.findAll();
    }

    public List<Periodical> findByTitle(String title) {
        return periodicalRepository.findByTitleIgnoreCase(title.trim());
    }

    public List<String> findAllTopics() {
        return periodicalRepository.findTopics();
    }
}
