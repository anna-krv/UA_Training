package game;

import java.util.Arrays;
import java.util.Scanner;

interface ConstantsHolder {
    // non - inclusive bounds
    int LOWER_BOUND = 0;
    int UPPER_BOUND = 100;
}

public class Controller {
    private Model model;
    private View view;
    private Scanner sc;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        sc = new Scanner(System.in);
    }

    /**
     * Read input with scanner.
     * Cast it to int. And check that it lies in valid range.
     * In case of mistakes print message and try again.
     *
     * @return number guessed
     */
    public int getGuess() {
        view.print(view.ASK_FOR_INPUT_MSG);
        String s = sc.next();
        int guess;
        try {
            guess = Integer.parseInt(s);
            if (!inCorrectRange(guess)) {
                view.print(view.WRONG_VALUE_MESSAGE);
                return getGuess();
            }
        } catch (NumberFormatException e) {
            view.print(view.WRONG_FORMAT_MESSAGE);
            return getGuess();
        }
        return guess;
    }



    /**
     * Check that user's guess lie in correct bounds
     *
     * @param guess user's guessed number
     * @return true if the guess is in correct bounds
     */
    public boolean inCorrectRange(int guess) {
        return guess > model.getLowerBound() && guess < model.getUpperBound();
    }

    /**
     * Control game flow until user guesses the number.
     * Ask user for input and send it to the model if it is successful.
     * Then make report on user action and send it to view.
     */
    public void process() {
        model.setUp(ConstantsHolder.LOWER_BOUND, ConstantsHolder.UPPER_BOUND);
        view.welcome(model.getLowerBound(), model.getUpperBound());
        while (!model.isGameEnded()) {
            int guess = getGuess();
            String res = model.processGuess(guess);
            if (!model.isGameEnded()) {
                view.report(guess, res, model.getGuessNums(), model.getLowerBound(), model.getUpperBound());
            }
        }
        view.finish(model.getGuessNums());
    }
}
