package app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public interface PatternContainer {
    /* Patterns that are the same for all locales */

    // pattern for string that consists of latin letters, underscore and digits
    Pattern NICKNAME_PATTERN = Pattern.compile("[A-Za-z0-9-_]{6,20}");
    // pattern for string that has 0-500 chars
    Pattern COMMENT_PATTERN = Pattern.compile(".{0,500}+");
    // pattern for string that represents a group name
    Pattern GROUP_PATTERN = getRegexForGroups();
    // pattern for +3809811111111 (+ followed by digits)
    Pattern PHONE_PATTERN = Pattern.compile("\\+[0-9]{8,15}");
    // pattern for empty string or phone like: +3809811111111 ('+' followed by digits)
    Pattern SECOND_PHONE_PATTERN = Pattern.compile("(^$)|(\\+[0-9]{8,15})");
    /* email : letter or digit + up to 63 letters or digits or special symbols + @ + domain
     + sub domain (0 or more) + top domain
     domain names are up to 253 char long
     example: john2doe@gmail.com
     */
    Pattern EMAIL_PATTERN = Pattern.compile(
            "[^_\\W][\\w!#$%&'*+-/=?^_`{|}]{0,63}@[A-Za-z0-9-]{1,253}(\\.[A-Za-z0-9-]{1,253})+");
    // string that is 6-32 char of length, starts with a letter, contains letters and digits
    Pattern SKYPE_PATTERN = Pattern.compile("[a-zA-Z][^_\\W]{5,35}");
    // postal code contains digits and whitespaces and letters
    Pattern POSTAL_CODE_PATTERN = Pattern.compile("([a-zA-Z0-9]\\s*){2,11}");
    // number that starts from non zero
    Pattern NUMBER_PATTERN = Pattern.compile("[1-9][0-9]*");

    /* Patterns for default EN locale */

    // pattern for string that consists of latin letters and hyphen
    Pattern WORD_PATTERN_EN = Pattern.compile("[A-Z][A-Za-z-]{1,20}");
    // one or a few words (no more than 3)
    Pattern WORD_COMBO_PATTERN_EN = Pattern.compile("([A-Za-z-]{1,100}\\s*){1,3}");

    /* Patterns for UA locale */

    // pattern for string that consists of latin letters and hyphen
    Pattern WORD_PATTERN_UA = Pattern.compile("[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє'-]{1,20}");
    // one or a few words (no more than 3)
    Pattern WORD_COMBO_PATTERN_UA = Pattern.compile("([А-ЩЬЮЯҐІЇЄа-щьюяґіїє'-]{1,100}\\s*){1,3}");

    static Pattern getRegexForGroups() {
        List<String> allGroups = new ArrayList<>();
        for (Note.Group group : Note.Group.values()) {
            allGroups.add(group.toString());
        }
        return Pattern.compile(String.join("|", allGroups), Pattern.CASE_INSENSITIVE);
    }
}

