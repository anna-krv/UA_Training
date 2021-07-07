package app;

import app.controller.Controller;
import app.model.Model;
import app.view.View;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new Model(), new View());
        controller.processUser();
    }
}
