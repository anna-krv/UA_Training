package app.model;

import app.model.entity.NotUniqueLoginException;
import app.model.entity.Note;
import app.model.entity.DBNotebook;


public class Model {
    private DBNotebook dbNotebook;

    public Model() {
        dbNotebook = new DBNotebook();
    }

    public void addNote(Note note) throws NotUniqueLoginException {
        dbNotebook.add(note);
    }
}
