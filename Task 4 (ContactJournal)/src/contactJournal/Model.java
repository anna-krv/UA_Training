package contactJournal;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private List<Contact> contacts;
    public Model(){
        contacts = new ArrayList<>();
    }
    public void addContact(Contact contact){
        contacts.add(contact);
    }
}
