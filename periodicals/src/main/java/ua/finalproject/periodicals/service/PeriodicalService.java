package ua.finalproject.periodicals.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    public List<Periodical> find(String title, String propertyForSort, List<String> topicsSelected) {
        if (title != null && !title.isEmpty()) {
            return findByTitle(title);
        }
        return topicsSelected == null ? findAllSorted(propertyForSort) :
                findAllByTopicsSorted(propertyForSort, topicsSelected);
    }

    public List<Periodical> findAllSorted(String propertyForSort) {
        return periodicalRepository.findAll(Sort.by(Sort.Direction.ASC, propertyForSort));
    }

    public List<Periodical> findAllByTopicsSorted(String propertyForSort, List<String> topicsSelected) {
        return periodicalRepository.findByTopicInIgnoreCase(topicsSelected,
                Sort.by(Sort.Direction.ASC, propertyForSort));
    }

    @Transactional
    public Periodical update(Long id, Periodical periodical) {
        Periodical periodicalInDb = periodicalRepository.getById(id);
        return periodicalRepository.save(periodicalInDb
                .toBuilder()
                .title(periodical.getTitle())
                .price(periodical.getPrice())
                .topic(periodical.getTopic())
                .build());
    }

    @Transactional
    public void deleteById(Long id) {
        subscriptionService.deleteByPeriodicalId(id);
        periodicalRepository.deleteById(id);
    }

    public List<Periodical> findByTitle(String title) {
        return periodicalRepository.findByTitleContainsIgnoreCase(title.trim());
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


}
