package ua.finalproject.periodicals.old.controller;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;
import ua.finalproject.periodicals.old.entity.Periodical;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RequestUtil {
    private static final String REGEX_PATH = Configurations.getProperty(Constants.PATH);
    private static Set<String> publicPath;

    static {
        publicPath = new HashSet<>();
        publicPath.addAll(Arrays.asList("", "index", "login", "loginPage", "register", "registerPage"));
    }

    public static String getPath(String uri) {
        return uri.replaceAll(REGEX_PATH, "");
    }

    public static boolean isPublic(String uri) {
        return publicPath.contains(getPath(uri));
    }

    public static boolean isLoginRequest(String uri) {
        String path = getPath(uri);
        return path.equals("loginPage") || path.equals("login");
    }

    public static boolean isAdminPath(String uri) {
        return getPath(uri).startsWith("admin");
    }

    public static int getNumberParam(HttpServletRequest request) {
        String numberStr = request.getParameter("number");
        int number = 0;
        try {
            number = Integer.valueOf(numberStr);
        } catch (NullPointerException | NumberFormatException ex) {
        }
        return number;
    }

    public static Long extractId(HttpServletRequest request, String targetBegin, String targetEnd) {
        String id = RequestUtil.getPath(request.getRequestURI())
                .replace(targetBegin, "")
                .replace(targetEnd, "");
        return Long.valueOf(id);
    }

    public static Periodical extractPeriodicalParam(HttpServletRequest request) {
        Periodical periodical= new Periodical();
        String title= request.getParameter("title");
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String topic = request.getParameter("topic");

        validatePeriodicalParams(title, price, topic);

        periodical.setTitle(title);
        periodical.setPrice(price);
        periodical.setTopic(topic);
        return periodical;
    }

    private static void validatePeriodicalParams(String title, BigDecimal price, String topic) {
        if (!check(title)){
            throw new IllegalArgumentException("title is invalid "+title);
        }
        if (!check(topic)){
            throw new IllegalArgumentException("topic is invalid "+topic);
        }
        if (price.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("price should be positive, but got "+price);
        }
    }

    private static boolean check(String s){
        return s!=null && s.length()>2;
    }
}
