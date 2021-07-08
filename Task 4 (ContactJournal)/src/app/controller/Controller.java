package app.controller;

import app.model.Model;
import app.model.entity.NotUniqueLoginException;
import app.model.entity.Note;
import app.view.View;

import java.util.Scanner;

/**
 * Class for handling communication between view(user) and model(business logic).
 * Validates data and sends it to model. Returns results to view.
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
        addNote();
        view.finishInput();
    }

    private Note addNote() {
        NoteBuilderFromInput noteBuilder = new NoteBuilderFromInput(view, sc);
        noteBuilder.inputNote();
        while (true) {
            try {
                Note note = noteBuilder.buildNote();
                model.addNote(note);
                return note;
            } catch (NotUniqueLoginException ex) {
                view.print(ex.toString());
                noteBuilder.inputLogin();
            }
        }
    }
}
