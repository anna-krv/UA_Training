package game;


import java.util.Arrays;

public class View {
    public final String WRONG_FORMAT_MESSAGE = "Your input is in wrong format. Expected and int number.\n";
    public final String ASK_FOR_INPUT_MSG = "\nPlease, input your guess:\t";
    public final String WRONG_VALUE_MESSAGE = "Your guess does not lie in the correct bounds.\n";

    /**
     * Print welcome message with rules of game.
     */
    void welcome(int lower, int upper) {
        print("Hello! Welcome to the game! " +
                "Your aim is to guess the number X. " +
                "The number X lies in (" + lower + ", " +
                upper + ").\n" +
                "When you make a guess n, I will tell you whether\n" +
                "n is greater than X: n > X,\n" +
                "n is less than X: n < X, \n" +
                "or n = X, which means that you have won!\n\n");
    }

    /**
     * Print congrats message and game statistics.
     *
     * @param nums array with numbers that were guessed by user.
     */
    void finish(int[] nums) {
        print("Congratulation! You have guessed the number!\n" +
                "You have used " + nums.length + " guesses. Here is the list of your guesses:\n");
        print(Arrays.toString(nums));
    }

    /**
     * Print analysis of user action and new bounds.
     */
    public void report(int guess, String res, int[] guessNums, int lowerBound, int upperBound) {
        String recommendation = res == ">" ? "Try lower: " : "Try higher: ";
        print(recommendation + guess + " " + res + " X. Your guesses: "
                + Arrays.toString(guessNums) + "\n" +
                "X is from (" + lowerBound + ", " + upperBound + ")\n");
    }

    /**
     * Print using System.out
     */
    void print(String s) {
        System.out.print(s);
    }
}
