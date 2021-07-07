package app.model;

import app.controller.Note;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Note> notes;
    public Model(){
        notes = new ArrayList<>();
    }
    public void addNote(Note note){
        notes.add(note);
    }
}
