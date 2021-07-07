package app.controller;

import app.model.Model;
import app.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Class for handling communication between view(user) and model(business logic).
 * Validate data and send it to model. Return results to view.
 */
public class Controller {
    private Model model;
    private View view;
    private Scanner sc;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
    }

    public void processUser() {
        InputNoteController noteController = new InputNoteController(view, sc);
        noteController.getNote();
    }
}
