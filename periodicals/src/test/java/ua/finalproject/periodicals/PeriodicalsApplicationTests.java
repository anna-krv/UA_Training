package ua.finalproject.periodicals;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.finalproject.periodicals.entity.MoneyAccountException;
import ua.finalproject.periodicals.entity.Periodical;
import ua.finalproject.periodicals.entity.User;
import ua.finalproject.periodicals.service.AccountService;
import ua.finalproject.periodicals.service.PeriodicalService;
import ua.finalproject.periodicals.service.SubscriptionService;
import ua.finalproject.periodicals.service.UserService;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeriodicalsApplicationTests {
    private final String username = "sherlock";
    private final String periodicalTitle = "National Geographic";
    private final BigDecimal BIG_SUM = BigDecimal.valueOf(300);
    @Autowired
    private SubscriptionService subscriptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private PeriodicalService periodicalService;
    @Autowired
    private AccountService accountService;
    private User user;
    private Periodical periodical;


    @Before
    public void init() {
        user = userService.findByUsername(username).get();
        accountService.putMoney(user.getAccount(), BIG_SUM);
        periodical = periodicalService.findByTitle(periodicalTitle).get(0);
    }

    @Test
    public void findSubscription() {
        try {
            subscriptionService.save(user, periodical);
            Assert.assertTrue(subscriptionService.findByUserAndPeriodical(user, periodical).isPresent());
        } catch (MoneyAccountException ex) {
            Assert.fail();
        }
        subscriptionService.delete(user, periodical);

        Assert.assertFalse(subscriptionService.findByUserAndPeriodical(user, periodical).isPresent());
    }

    @Test
    public void createAndDeleteSubscription() {
        try {
            subscriptionService.save(user, periodical);
            Assert.assertTrue(subscriptionService.findByUserAndPeriodical(user, periodical).isPresent());
        } catch (MoneyAccountException ex) {
            Assert.fail();
        }
        subscriptionService.delete(user, periodical);

        Assert.assertFalse(subscriptionService.findByUserAndPeriodical(user, periodical).isPresent());
    }


    @Test
    public void createAndDeletePeriodical() {
        Periodical periodical = Periodical.builder()
                .title("CreateAndDeleteTestPeriodical")
                .price(BigDecimal.valueOf(100))
                .topic("test_topic")
                .build();
        try {
            Long periodicalId = periodicalService.save(periodical).getId();
            subscriptionService.save(user, periodical);

            Assert.assertTrue(periodicalService.findById(periodicalId).isPresent());
            Assert.assertTrue(subscriptionService.findByUserIdAndPeriodicalId(user.getId(), periodicalId).isPresent());

            periodicalService.deleteById(periodicalId);

            Assert.assertFalse(periodicalService.findById(periodicalId).isPresent());
            Assert.assertEquals(0, subscriptionService.findByPeriodicalId(periodicalId).size());
        } catch (Exception ex) {
            Assert.fail();
        }
    }

}
