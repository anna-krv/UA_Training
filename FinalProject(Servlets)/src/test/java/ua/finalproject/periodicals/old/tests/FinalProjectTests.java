package ua.finalproject.periodicals.old.tests;

import org.junit.Assert;
import org.junit.Test;
import ua.finalproject.periodicals.old.controller.RequestUtil;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.Criteria;
import ua.finalproject.periodicals.old.service.PeriodicalService;
import ua.finalproject.periodicals.old.service.SubscriptionService;
import ua.finalproject.periodicals.old.service.UserService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;


public class FinalProjectTests {
    private final String TEST_TITLE = "Love to cook";
    private static PeriodicalService periodicalService ;
    private  static UserService userService ;
    private static SubscriptionService subscriptionService;
    //@BeforeAll
    public static void setUp(){
        periodicalService = new PeriodicalService();
        userService = new UserService();
        subscriptionService = new SubscriptionService();
    }
    @Test
    public void periodicalDaoInsertAndDeleteTest() throws SQLException {
        Periodical periodical = new Periodical();
        periodical.setPrice(BigDecimal.valueOf(234));
        periodical.setTitle(TEST_TITLE);
        periodical.setTopic("culinary");
        periodicalService.create(periodical);
        periodicalService.deleteByTitle(TEST_TITLE);
    }

    @Test
    public void createSubscription() throws SQLException {
        Periodical periodical = periodicalService.findAll(0).get(0);
        User user = userService.findById(Long.valueOf(1)).get();

        //subscriptionService.create(user, periodical);
        subscriptionService.delete(user, periodical);
    }

    @Test
    public void findPeriodicalsByCriteria() {
        periodicalService
                .findTopics()
                .stream()
                .forEach(System.out::println);
        Assert.assertTrue(periodicalService
                .findByCriteria(new Criteria(
                        "ID",
                        0,
                        "",
                        new ArrayList<String>(),
                        Optional.ofNullable(Long.valueOf(2))))
                .stream()
                .count() > 0);
    }

    @Test
    public void pathUtilTest(){
        System.out.println(RequestUtil.getPath("periodicals_servlets__war"));
    }

}
