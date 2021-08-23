package ua.finalproject.periodicals.old.controller;

import ua.finalproject.periodicals.old.config.Configurations;
import ua.finalproject.periodicals.old.config.Constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class PathUtil {
    private static final String REGEX_PATH = Configurations.getProperty(Constants.PATH);
    private static final Logger logger = Logger.getLogger(PathUtil.class.getName());
    private static Set<String> publicPath;

    static {
        publicPath = new HashSet<>();
        publicPath.addAll(Arrays.asList("", "index", "login", "loginPage", "register", "registerPage"));
    }

    public static String getPath(String uri) {
        return uri.replaceAll(REGEX_PATH, "");
    }

    public static boolean isPublic(String uri) {
        logger.info(getPath(uri));
        return publicPath.contains(getPath(uri));
    }

    public static boolean isLoginRequest(String uri) {
        String path = getPath(uri);
        return path.equals("loginPage") || path.equals("login");
    }
}
