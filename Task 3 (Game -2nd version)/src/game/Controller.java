package game;

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
     * Set up the game and control game flow until user guesses the number.
     */
    public void processUser() {
        model.setUp(ConstantsHolder.LOWER_BOUND, ConstantsHolder.UPPER_BOUND);
        view.welcome(model.getLowerBound(), model.getUpperBound());
        while (model.isGameOn()) {
            processGuess();
        }
        view.print(new Report(model.getGuessNums()).getReportOnFinish());
    }

    /**
     * Get a valid guess from user, process it by model and report the result.
     */
    public void processGuess() {
        String result = model.processGuess(getValidGuess());
        if (model.isGameOn()) {
            Report report = new Report(result, model.getGuessNums(), model.getLowerBound(), model.getUpperBound());
            view.print(report.getReportOnUserAction());
        }
    }

    /**
     * Read input with scanner until it gets in valid range.
     *
     * @return number guessed that was verified by range test.
     */
    public int getValidGuess() {
        Integer guess = null;

        while (guess == null || !inCorrectRange(guess)) {
            if (guess != null) {
                view.print(view.WRONG_RANGE_MESSAGE);
            }
            view.print(view.ASK_FOR_INPUT);
            skipScannerUntilNextInt();
            guess = sc.nextInt();
        }
        return guess;
    }

    private void skipScannerUntilNextInt() {
        while (!sc.hasNextInt()) {
            sc.next();
            view.print(view.WRONG_FORMAT_MESSAGE);
            view.print(view.ASK_FOR_INPUT);
        }
    }

    public boolean inCorrectRange(int guess) {
        return guess > model.getLowerBound() && guess < model.getUpperBound();
    }
}
