package app.controller;

import app.view.View;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class that is responsible for getting valid input.
 * Checks input with regex.
 */
public class UtilityController {
    private View view;
    private Scanner sc;

    public UtilityController(View view, Scanner sc) {
        this.view = view;
        this.sc = sc;
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

    public void setScannerToFile(String filename) {
        try {
            sc = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
