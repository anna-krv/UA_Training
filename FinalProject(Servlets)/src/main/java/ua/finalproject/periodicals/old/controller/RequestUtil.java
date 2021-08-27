package ua.finalproject.periodicals.old.controller;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;
import ua.finalproject.periodicals.old.entity.Periodical;
import ua.finalproject.periodicals.old.entity.User;
import ua.finalproject.periodicals.old.service.Criteria;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestUtil {
    private static final String REGEX_PATH = Configurations.getProperty(Constants.PATH);
    private static final String REGEX_NUMBER = "[0-9]+";
    private static final String REGEX_EMAIL = "[^_\\W][\\w!#$%&'*+-/=?^_`{|}]{0,63}@[A-Za-z0-9-]{1,253}(\\.[A-Za-z0-9-]{1,253})+";
    private static final String REGEX_USERNAME = "[A-Za-z0-9-_]{5,20}";
    private static final String REGEX_WORD = ".{2,20}";
    private static final String REGEX_PASSWORD = ".{4,30}";
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

    public static Long extractId(HttpServletRequest request) {
        String path = getPath(request.getRequestURI());
        Matcher matcher = Pattern.compile(REGEX_NUMBER).matcher(path);
        matcher.find();
        return Long.valueOf(matcher.group());
    }

    public static Periodical extractPeriodical(HttpServletRequest request) {
        Periodical periodical = new Periodical();
        String title = getStrParameter("title", REGEX_WORD, request);
        BigDecimal price = getPositiveBigDecimal("price", request);
        String topic = getStrParameter("topic", REGEX_WORD, request);

        periodical.setTitle(title);
        periodical.setPrice(price);
        periodical.setTopic(topic);
        return periodical;
    }

    private static BigDecimal getPositiveBigDecimal(String paramName, HttpServletRequest request) {
        BigDecimal price = new BigDecimal(request.getParameter(paramName));
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("price should be positive, but got " + price);
        }
        return price;

    }

    private static String getStrParameter(String paramName, String regex, HttpServletRequest request) {
        String value = request.getParameter(paramName);
        check(value, regex, paramName);
        return value;
    }

    private static void check(String value, String regex, String paramName) {
        if (value == null || !value.matches(regex)) {
            throw new IllegalArgumentException(value + " is an invalid parameter for " + paramName);
        }
    }

    public static User extractUser(HttpServletRequest request) {
        String name = getStrParameter("name", REGEX_WORD, request);
        String surname = getStrParameter("surname", REGEX_WORD, request);
        String email = getStrParameter("email", REGEX_EMAIL, request);
        String username = getStrParameter("username", REGEX_USERNAME, request);
        String password = getStrParameter("password", REGEX_PASSWORD, request);

        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public static RequestAction getRequestAction(HttpServletRequest request) {
        String path = RequestUtil.getPath(request.getRequestURI());
        if (path.endsWith("unsubscribe")) {
            return RequestAction.UNSUBSCRIBE;
        }
        if (path.endsWith("subscribe")) {
            return RequestAction.SUBSCRIBE;
        }
        if (path.endsWith("changeBlockStatus")) {
            return RequestAction.CHANGE_BLOCK_STATUS;
        }
        return RequestAction.GET;
    }

    public static User extractUserNoValidation(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter("name"));
        user.setSurname(request.getParameter("surname"));
        user.setEmail(request.getParameter("email"));
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));

        return user;
    }

    public static Criteria extractCriteria(HttpServletRequest request) {
        String title = request.getParameter("search");
        String fieldForSort = request.getParameter("sort");
        String[] topicsSelectedArr = request.getParameterValues("topic");

        List<String> topicsSelected = topicsSelectedArr != null ? Arrays.asList(topicsSelectedArr) : new ArrayList<>();
        int number = getNumberParam(request);
        Long userId = (Long) request.getSession().getAttribute("userId");

        return new Criteria(fieldForSort, number, title, topicsSelected, Optional.of(userId));
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

    public static void setRequestAttrFromCriteria(HttpServletRequest request, Criteria criteria) {
        request.setAttribute("number", criteria.getNumber());
        request.setAttribute("topicsSelected", criteria.getTopics());
        request.setAttribute("sort", criteria.getFieldForSort().toString());
        request.setAttribute("search", criteria.getTitle());
        request.setAttribute("personalPage", criteria.getUserId().isPresent());
    }
}
