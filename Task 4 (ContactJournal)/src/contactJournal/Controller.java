package contactJournal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Controller {
    // pattern for string that consists of latin letters and hyphen
    public final Pattern WORD_PATTERN = Pattern.compile("[A-Za-z-]+");
    // pattern for string that consists of latin letters, underscore and digits
    public final Pattern NICKNAME_PATTERN = Pattern.compile("[A-Za-z0-9_]+");
    // pattern for string that has 0-500 chars
    public final Pattern COMMENT_PATTERN = Pattern.compile(".{0,500}+");
    // pattern for string that represents a group name
    public final Pattern GROUP_PATTERN;
    // pattern for +3809811111111 (+ followed by digits)
    public final Pattern PHONE_PATTERN = Pattern.compile("\\+[0-9]{8,15}");
    // pattern for empty string or phone like: +3809811111111 ('+' followed by digits)
    public final Pattern SECOND_PHONE_PATTERN = Pattern.compile("(^$)|(\\+[0-9]{8,15})");
    /* email : letter or digit + up to 63 letters or digits or special symbols + @ + domain
     + sub domain (0 or more) + top domain
     domain names are up to 253 char long
     example: john2doe@gmail.com
     */
    public final Pattern EMAIL_PATTERN = Pattern.compile(
            "[^_\\W][\\w!#$%&'*+-/=?^_`{|}]{0,63}@[A-Za-z0-9-]{1,253}(\\.[A-Za-z0-9-]{1,253})+");
    // string that is 6-32 char of length, starts with a letter, contains letters and digits
    public final Pattern SKYPE_PATTERN = Pattern.compile("[a-zA-Z][^_\\W]{5,35}");
    // postal code contains digits and whitespaces and letters
    public final Pattern POSTAL_CODE_PATTERN = Pattern.compile("([a-zA-Z0-9]\\s*){2,11}");
    // one or a few words (no more than 5)
    public final Pattern WORD_COMBO_PATTERN = Pattern.compile("([A-Za-z-]{1,100}\\s*){1,5}");
    // number that starts from non zero
    public final Pattern NUMBER_PATTERN = Pattern.compile("[1-9][0-9]*");
    private Model model;
    private View view;
    private Scanner sc;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
        GROUP_PATTERN = Pattern.compile(getRegexForGroups(), Pattern.CASE_INSENSITIVE);
    }


    /**
     * Get contact information and add it to model.
     */
    public void register() {
        model.addContact(getContact());
    }

    /**
     * Read scanner until getting all needed info for creation of a contact.
     *
     * @return new contact created.
     */
    public Contact getContact() {
        String surname, name, middleName, compoundName, nickname, comment, groupName;
        String homeNumber, mobileNumber, secondMobileNumber;
        String email, skype;
        Address address;

        surname = getField("surname", WORD_PATTERN);
        name = getField("name", WORD_PATTERN);
        middleName = getField("middleName", WORD_PATTERN);
        nickname = getField("nickname", NICKNAME_PATTERN);
        comment = getField("comment", COMMENT_PATTERN);
        groupName = getField("group", GROUP_PATTERN);
        homeNumber = getField("home phone number", PHONE_PATTERN);
        mobileNumber = getField("mobile phone", PHONE_PATTERN);
        secondMobileNumber = getField("mobile phone #2", SECOND_PHONE_PATTERN);
        email = getField("email", EMAIL_PATTERN);
        skype = getField("skype", SKYPE_PATTERN);
        address = getAddress();
        return new Contact(surname, name, middleName, nickname, comment,
                groupName, homeNumber, mobileNumber, secondMobileNumber, email, skype,
                address);
    }

    public String getField(String fieldName, Pattern patternForCheck) {
        String field = "";
        boolean gotField = false;

        while (!gotField) {
            view.askFor(fieldName);
            String input = sc.nextLine().trim();
            if (check(input, patternForCheck)) {
                field = input;
                gotField = true;
            } else {
                view.reportErrorIn(fieldName);
            }
        }
        return field;
    }

    public boolean check(String whatToCheck, Pattern pattern) {
        return pattern.matcher(whatToCheck).matches();
    }

    public Address getAddress() {
        String postalCode, city, street, house, flat;

        postalCode = getField("index (postal code)", POSTAL_CODE_PATTERN);
        city = getField("city", WORD_COMBO_PATTERN);
        street = getField("street", WORD_COMBO_PATTERN);
        house = getField("house #", NUMBER_PATTERN);
        flat = getField("flat #", NUMBER_PATTERN);
        return new Address(postalCode, city, street, Integer.valueOf(house), Integer.valueOf(flat));
    }

    public void setScannerToFile(String filename) {
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String getRegexForGroups() {
        List<String> allGroups = new ArrayList<>();
        for (Contact.Group group : Contact.Group.values()) {
            allGroups.add(group.toString());
        }
        return String.join("|", allGroups);
    }
}
