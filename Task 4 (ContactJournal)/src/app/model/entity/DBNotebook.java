package app.model.entity;

import java.util.ArrayList;
import java.util.List;

public class DBNotebook {
    private List<Note> notes;

    public DBNotebook() {
        notes = new ArrayList<Note>();
        notes.add(new Note("Newton", "Isaac", "James", "iamisaac",
                "", "OTHER", "", "", "",
                "", "", new Address()));
        notes.add(new Note("Ньютон", "Ісаак", "Джеймс", "isaac123",
                "", "OTHER", "", "", "",
                "", "", new Address()));
    }

    public void add(Note note) throws NotUniqueLoginException {
        if (!check(note)) {
            throw new NotUniqueLoginException(note.getLogin());
        }
        notes.add(note);
    }

    private boolean check(Note note) {
        for (Note savedNote : notes) {
            if (savedNote.getLogin().equalsIgnoreCase(note.getLogin())) {
                return false;
            }
        }
        return true;
    }
}
