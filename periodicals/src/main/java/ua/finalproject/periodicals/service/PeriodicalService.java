package ua.finalproject.periodicals.service;

import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.repository.PeriodicalRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PeriodicalService {
    private final PeriodicalRepository periodicalRepository;
    private final SubscriptionService subscriptionService;

    public PeriodicalService(PeriodicalRepository periodicalRepository, SubscriptionService subscriptionService) {
        this.periodicalRepository = periodicalRepository;
        this.subscriptionService = subscriptionService;
    }

    public List<Periodical> find(String title, String sortBy, List<String> topicsSelected) {
        if (title != null && !title.isEmpty()) {
            return findByTitle(title);
        }
        if (topicsSelected == null) {
            return findAllSorted(sortBy);
        }
        return findAllByTopicsSorted(sortBy, topicsSelected);
    }

    public List<Periodical> findAllSorted(String sortBy) {
        switch (sortBy) {
            case "title":
                return periodicalRepository.findAllByOrderByTitleAsc();
            case "price":
                return periodicalRepository.findAllByOrderByPriceAsc();
            default:
                return null;
        }
    }

    public List<Periodical> findAllByTopicsSorted(String sortBy, List<String> topicsSelected) {
        switch (sortBy) {
            case "title":
                return periodicalRepository.findByTopicInIgnoreCaseOrderByTitleAsc(topicsSelected);
            case "price":
                return periodicalRepository.findByTopicInIgnoreCaseOrderByPriceAsc(topicsSelected);
            default:
                return null;
        }
    }

    public List<Periodical> findByTitle(String title) {
        return periodicalRepository.findByTitleIgnoreCase(title.trim());
    }

    public List<String> findAllTopics() {
        return periodicalRepository.findTopics();
    }

    public Optional<Periodical> findById(Long id) {
        return periodicalRepository.findById(id);
    }

    public Periodical save(Periodical periodical) {
        return periodicalRepository.save(periodical);
    }

    public List<Periodical> findAll() {
        return periodicalRepository.findAll();
    }

    public Periodical update(Long id, Periodical periodical) {
        Periodical periodicalInDb = periodicalRepository.findById(id).get();
        periodicalInDb.setTitle(periodical.getTitle());
        periodicalInDb.setPrice(periodical.getPrice());
        periodicalInDb.setTopic(periodical.getTopic());
        return periodicalRepository.save(periodicalInDb);
    }

    public void deleteById(Long id) {
        subscriptionService.deleteByPeriodicalId(id);
        periodicalRepository.nativeDelete(id);
    }
}
