package ua.finalproject.periodicals.old.controller;

import ua.finalproject.periodicals.old.controller.command.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Servlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Servlet.class.getName());

    private static Map<String, Command> commands;

    public Servlet() {
        commands = new HashMap<>();
        commands.put("loginPage", new LoginPage());
        commands.put("login", new Login());
        commands.put("registerPage", new RegisterPage());
        commands.put("register", new Register());
        commands.put("logout", new Logout());
        commands.put("periodicals", new Periodicals());
        commands.put("periodicals/subscribed", new PeriodicalsSubscribed());
        commands.put("periodicals/[0-9]+.*", new PeriodicalById());
        commands.put("account(\\?.*)*", new Account());

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String path = PathUtil.getPath(request.getRequestURI());
        logger.info("path " + path);
        Command command = getCommand(path);//commands.getOrDefault(path, (req) -> "/index.jsp");
        String page = command.execute(request);
        logger.info("next page "+page);
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }

    private Command getCommand(String path) {
        String commandKey = commands
                .keySet()
                .stream()
                .filter(key -> path.matches(key))
                .findFirst()
                .orElse("");
        logger.info("command with key: "+commandKey);
        return commands.getOrDefault(commandKey, (req) -> "index.jsp");
    }
}
