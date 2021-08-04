package ua.finalproject.periodicals.service;

import org.springframework.stereotype.Service;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.Topic;
import ua.finalproject.periodicals.repository.PeriodicalRepository;

import java.util.List;

@Service
public class PeriodicalService {
    private final PeriodicalRepository periodicalRepository;

    public PeriodicalService(PeriodicalRepository periodicalRepository) {
        this.periodicalRepository = periodicalRepository;
    }

    public List<Periodical> findAll() {
        return periodicalRepository.findAll();
    }

    public List<Periodical> findByTitle(String title) {
        return periodicalRepository.findByTitleIgnoreCase(title.trim());
    }

    //@PostConstruct
    public void init() {
        Periodical p = Periodical.builder()
                .title("Forbes").price(7500).topic(Topic.NEWS).build();
        periodicalRepository.save(p);
        p = Periodical.builder()
                .title("Vogue").price(10200).topic(Topic.FASHION).build();
        periodicalRepository.save(p);
        p = Periodical.builder()
                .title("Футбол").price(11200).topic(Topic.SPORT).build();
        periodicalRepository.save(p);
        p = Periodical.builder()
                .title("Риболовля").price(7300).topic(Topic.LEISURE).build();
        periodicalRepository.save(p);
    }


}
