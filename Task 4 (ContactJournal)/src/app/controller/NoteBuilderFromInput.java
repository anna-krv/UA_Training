package app.controller;

import app.model.entity.Address;
import app.model.entity.Note;
import app.view.TextConstant;
import app.view.View;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that is responsible for creation of note from input.
 */
public class NoteBuilderFromInput implements TextConstant, PatternContainer {
    public Pattern WORD_PATTERN;
    public Pattern WORD_COMBO_PATTERN;
    private View view;
    private Scanner sc;
    private UtilityController utilityController;
    private String surname, name, middleName, login, comment, groupName;
    private String homeNumber, mobileNumber, secondMobileNumber;
    private String email, skype;
    private Address address;

    public NoteBuilderFromInput(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
        utilityController = new UtilityController(view, sc);
        setUpForLocale();
    }

    /**
     * Select patterns that are suited for the locale defined in view.
     */
    private void setUpForLocale() {
        boolean isEnLocale = view.getLocale().getLanguage().equals("en");
        WORD_PATTERN = isEnLocale ? WORD_PATTERN_EN : WORD_PATTERN_UA;
        WORD_COMBO_PATTERN = isEnLocale ? WORD_COMBO_PATTERN_EN : WORD_COMBO_PATTERN_UA;
    }

    /**
     * Input from user all info.
     */
    public void inputNote() {
        inputFullName();
        inputLogin();
        inputAdditionalInfo();
        inputContactDetails();
        inputAddress();
    }

    public void inputFullName() {
        surname = utilityController.inputValue(SURNAME, WORD_PATTERN);
        name = utilityController.inputValue(FIRST_NAME, WORD_PATTERN);
        middleName = utilityController.inputValue(MIDDLE_NAME, WORD_PATTERN);
    }

    public void inputLogin() {
        login = utilityController.inputValue(LOGIN, NICKNAME_PATTERN);
    }

    public void inputAdditionalInfo() {
        comment = utilityController.inputValue(COMMENT, COMMENT_PATTERN);
        groupName = utilityController.inputValue(GROUP, GROUP_PATTERN);
    }

    public void inputContactDetails() {
        homeNumber = utilityController.inputValue(HOME_PHONE, PHONE_PATTERN);
        mobileNumber = utilityController.inputValue(MOBILE_PHONE, PHONE_PATTERN);
        secondMobileNumber = utilityController.inputValue(SECOND_MOBILE_PHONE, SECOND_PHONE_PATTERN);
        email = utilityController.inputValue(EMAIL, EMAIL_PATTERN);
        skype = utilityController.inputValue(SKYPE, SKYPE_PATTERN);
    }

    public void inputAddress() {
        String postalCode, city, street, house, flat;

        postalCode = utilityController.inputValue(POSTAL_CODE, POSTAL_CODE_PATTERN);
        city = utilityController.inputValue(CITY, WORD_COMBO_PATTERN);
        street = utilityController.inputValue(STREET, WORD_COMBO_PATTERN);
        house = utilityController.inputValue(HOUSE, NUMBER_PATTERN);
        flat = utilityController.inputValue(FLAT, NUMBER_PATTERN);
        address = new Address(postalCode, city, street, Integer.valueOf(house), Integer.valueOf(flat));
    }

    /**
     * Build note using saved info from input.
     *
     * @return note
     */
    public Note buildNote() {
        return new Note(surname, name, middleName, login, comment,
                groupName, homeNumber, mobileNumber, secondMobileNumber, email, skype,
                address);
    }
}
