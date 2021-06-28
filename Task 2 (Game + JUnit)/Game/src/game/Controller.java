package game;

import java.util.Arrays;
import java.util.Scanner;

public class Controller {
    private Model model;
    private View view;
    private Scanner sc;
    private final String WRONG_FORMAT_MESSAGE = "Your input is in wrong format. Expected and int number.\n";
    private final String ASK_FOR_INPUT_MSG = "Please, input your guess:\t";
    private final String WRONG_VALUE_MESSAGE = "Your guess does not lie in the correct bounds.\n";

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
    }

    /**
     * Read input with scanner.
     * If possible cast it to int.
     *
     * @return number guessed
     */
    public int getGuess() {
        view.print(ASK_FOR_INPUT_MSG);
        String s = sc.next();
        return Integer.parseInt(s);
    }

    /**
     * Print analysis of user action and new bounds.
     */
    private void report(int guess, String res) {
        String recommendation = res == ">" ? "Try lower: " : "Try higher: ";
        view.print(recommendation + guess + " " + res + " X. Your guesses: "
                + Arrays.toString(model.getGuessNums()) + "\n" +
                "X is from [" + model.getLowerBound() + ", " + model.getUpperBound() + "]\n");
    }

    /**
     * Control game flow until user guesses the number.
     * Ask user for input and send it to the model if it is successful.
     * Then make report on user action and send it to view.
     */
    public void process() {
        int guess;
        String res;

        view.welcome(+model.getLowerBound(), +model.getUpperBound());
        while (!model.isGameEnded()) {
            try {
                guess = getGuess();
                if (model.isValidGuess(guess)) {
                    res = model.processGuess(guess);
                    if (!model.isGameEnded()) {
                        report(guess, res);
                    }
                } else {
                    view.print(WRONG_VALUE_MESSAGE);
                }
            } catch (NumberFormatException e) {
                view.print(WRONG_FORMAT_MESSAGE);
            }
        }
        view.finish(model.getGuessNums());
    }
}
