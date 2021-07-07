package app.controller;

import app.view.TextConstant;
import app.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that handles input of a note.
 */
public class InputNoteController implements TextConstant, PatternContainer {
    public Pattern WORD_PATTERN;
    public Pattern WORD_COMBO_PATTERN;
    private View view;
    private Scanner sc;

    public InputNoteController(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
        setUpForLocale();
    }

    private void setUpForLocale() {
        boolean isEnLocale = view.getLocale().getLanguage().equals("en");
        WORD_PATTERN = isEnLocale ? WORD_PATTERN_EN : WORD_PATTERN_UA;
        WORD_COMBO_PATTERN = isEnLocale ? WORD_COMBO_PATTERN_EN : WORD_COMBO_PATTERN_UA;
    }

    /**
     * Read scanner until getting all needed info for creation of a note.
     */
    public Note getNote() {
        String surname, name, middleName, nickname, comment, groupName;
        String homeNumber, mobileNumber, secondMobileNumber;
        String email, skype;
        Address address;

        surname = inputValue(SURNAME, WORD_PATTERN);
        name = inputValue(FIRST_NAME, WORD_PATTERN);
        middleName = inputValue(MIDDLE_NAME, WORD_PATTERN);
        nickname = inputValue(LOGIN, NICKNAME_PATTERN);
        comment = inputValue(COMMENT, COMMENT_PATTERN);
        groupName = inputValue(GROUP, GROUP_PATTERN);
        homeNumber = inputValue(HOME_PHONE, PHONE_PATTERN);
        mobileNumber = inputValue(MOBILE_PHONE, PHONE_PATTERN);
        secondMobileNumber = inputValue(SECOND_MOBILE_PHONE, SECOND_PHONE_PATTERN);
        email = inputValue(EMAIL, EMAIL_PATTERN);
        skype = inputValue(SKYPE, SKYPE_PATTERN);
        address = getAddress();
        view.finishInput();
        return new Note(surname, name, middleName, nickname, comment,
                groupName, homeNumber, mobileNumber, secondMobileNumber, email, skype,
                address);
    }

    public String inputValue(String valueToGet, Pattern patternForCheck) {
        while (true) {
            view.askFor(valueToGet, patternForCheck);
            String input = sc.nextLine().trim();
            if (patternForCheck.matcher(input).matches()) {
                return input;
            }
            view.reportErrorIn(valueToGet);
        }
    }

    public boolean check(String whatToCheck, Pattern pattern) {
        return pattern.matcher(whatToCheck).matches();
    }

    public Address getAddress() {
        String postalCode, city, street, house, flat;

        postalCode = inputValue(POSTAL_CODE, POSTAL_CODE_PATTERN);
        city = inputValue(CITY, WORD_COMBO_PATTERN);
        street = inputValue(STREET, WORD_COMBO_PATTERN);
        house = inputValue(HOUSE, NUMBER_PATTERN);
        flat = inputValue(FLAT, NUMBER_PATTERN);
        return new Address(postalCode, city, street, Integer.valueOf(house), Integer.valueOf(flat));
    }

    public void setScannerToFile(String filename) {
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
