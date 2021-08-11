package ua.finalproject.periodicals.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.User;
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

    public Page<Periodical> find(String title, String propertyForSort, List<String> topicsSelected, int number) {
        if (title != null && !title.isEmpty()) {
            return findByTitle(title, propertyForSort, number);
        }
        return topicsSelected == null ? findAllSorted(propertyForSort, number) :
                findAllByTopicsSorted(propertyForSort, topicsSelected, number);
    }

    public Page<Periodical> findByTitle(String title, String propertyForSort, int number) {
        return periodicalRepository.findByTitleContainsIgnoreCase(title.trim(),
                PageRequest.of(number, AppConstants.PAGE_SIZE, Sort.by(Sort.Direction.ASC, propertyForSort)));
    }

    public Page<Periodical> findAllSorted(String propertyForSort, int number) {
        return periodicalRepository.findAll(PageRequest.of(number, AppConstants.PAGE_SIZE, Sort.by(Sort.Direction.ASC, propertyForSort)));
    }

    public Page<Periodical> findAllByTopicsSorted(String propertyForSort, List<String> topicsSelected, int number) {
        return periodicalRepository.findAllByTopicInIgnoreCase(topicsSelected,
                PageRequest.of(number, AppConstants.PAGE_SIZE, Sort.by(Sort.Direction.ASC, propertyForSort)));
    }

    public Page<Periodical> findForUser(User user, String title, String propertyForSort, List<String> topicsSelected, int number) {
        if (title != null && !title.isEmpty()) {
            return findForUserByTitle(user, title, propertyForSort, number);
        }
        return topicsSelected == null ? findForUserSorted(user, propertyForSort, number) :
                findForUserByTopicsSorted(user, propertyForSort, topicsSelected, number);

    }


    private Page<Periodical> findForUserByTitle(User user, String title, String propertyForSort, int number) {
        return periodicalRepository.findByUserIdAndTitleContainsIgnoreCase(user.getId(), title.trim(),
                PageRequest.of(number, AppConstants.PAGE_SIZE, Sort.by(Sort.Direction.ASC, propertyForSort)));

    }

    private Page<Periodical> findForUserSorted(User user, String propertyForSort, int number) {
        return periodicalRepository.findByUserId(user.getId(),
                PageRequest.of(number, AppConstants.PAGE_SIZE, Sort.by(Sort.Direction.ASC, propertyForSort)));
    }

    private Page<Periodical> findForUserByTopicsSorted(User user, String propertyForSort, List<String> topicsSelected, int number) {
        return periodicalRepository.findByUserIdAndTopicInIgnoreCase(user.getId(), topicsSelected,
                PageRequest.of(number, AppConstants.PAGE_SIZE, Sort.by(Sort.Direction.ASC, propertyForSort)));
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
