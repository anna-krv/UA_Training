package game;


import java.util.Arrays;

class Report {
    private String result;
    private int[] guesses;
    private int lowerBound, upperBound;

    public Report(String result, int[] guesses, int lowerBound, int upperBound) {
        this.result = result;
        this.guesses = guesses;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public Report(int[] guesses) {
        this.guesses = guesses;
    }

    /**
     * Get analysis of user action.
     *
     * @return string with analysis of user's action and game state.
     */
    public String getReportOnUserAction() {
        String recommendation = result == ">" ? "Try lower: " : "Try higher: ";
        int n = guesses.length;

        return recommendation + guesses[n - 1] + " " + result + " X. Your guesses: "
                + Arrays.toString(guesses) + "\n" +
                "X is from (" + lowerBound + ", " + upperBound + ")\n";
    }

    /**
     * Get message with congratulation and game stats.
     *
     * @return string with info about game end.
     */
    public String getReportOnFinish() {
        int n = guesses.length;

        return "Congratulation! You have guessed the number: " + guesses[n - 1] + "\n" +
                "You have used " + n + " guesses. Here is the list of your guesses:\n" +
                Arrays.toString(guesses);
    }
}

public class View {
    public final String WRONG_FORMAT_MESSAGE = "Your input is in wrong format. Expected and int number.\n";
    public final String ASK_FOR_INPUT = "\nPlease, enter your guess:\t";
    public final String WRONG_RANGE_MESSAGE = "Your guess does not lie in the correct bounds.\n";

    /**
     * Print welcome message with rules of game.
     */
    void welcome(int lower, int upper) {
        print("Hello!\nWelcome to the game \"More or Less\"!\n" +
                "Your aim is to guess the number X. " +
                "The number X lies in (" + lower + ", " +
                upper + ").\n" +
                "When you make a guess n, I will tell you if\n" +
                "n > X, n < X or n = X, which means that you are the winner!\n");
    }

    void print(String s) {
        System.out.print(s);
    }
}
