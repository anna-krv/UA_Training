package app.view;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class View implements TextConstant {
    static String MESSAGES_BUNDLE_NAME = "messages";
    static Locale locale = new Locale("ua", "UA");
    public static final ResourceBundle bundle = ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME,
            locale);

    /**
     * Print message asking for a field.
     * @param fieldToGet
     * @param patternForCheck is used to provide hint for input format
     */
    public void askFor(String fieldToGet, Pattern patternForCheck) {
        print(concat(bundle.getString(ASK_FOR_INPUT), bundle.getString(fieldToGet),
                bundle.getString(FORMAT_DATA), "[", patternForCheck.toString(), "]"));
    }

    public void reportErrorIn(String fieldName) {
        print(concat(bundle.getString(FORMAT_ERROR),
                bundle.getString(fieldName)));

    }

    public void finishInput() {
        print(bundle.getString(FINISH_DATA));
    }

    public void print(String whatToPrint) {

        System.out.println(whatToPrint);
    }

    private String concat(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String item : strings) {
            sb.append(item);
        }
        return sb.toString();
    }

    public Locale getLocale() {
        return locale;
    }
}
