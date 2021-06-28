package game;

import java.util.Arrays;
import java.util.Random;

public class Model {
    private final int LOWER_BOUND = 0;
    private final int UPPER_BOUND = 100;     // inclusive bound
    private int correctNum;
    private int[] guessNums;
    private int nGuesses;
    private boolean gameEnded;
    private int lowerBound;
    private int upperBound;             // inclusive bound

    /**
     * Set up the game.
     * Select random number in correct bounds.
     * Initialize array with guesses.
     */
    public Model() {
        correctNum = LOWER_BOUND + new Random().nextInt(
                UPPER_BOUND - LOWER_BOUND + 1
        );
        guessNums = new int[UPPER_BOUND - LOWER_BOUND + 1];
        gameEnded = false;
        nGuesses = 0;
        lowerBound = LOWER_BOUND;
        upperBound = UPPER_BOUND;
    }

    /**
     * Check that user's guess lie in correct bounds
     *
     * @param guess user's guessed number
     * @return true if the guess is in correct bounds
     */
    public boolean isValidGuess(int guess) {
        return guess >= lowerBound && guess <= upperBound;
    }

    /**
     * Get lower bound for correct number.
     *
     * @return number that &le; correct number
     */
    public int getLowerBound() {
        return lowerBound;
    }

    /**
     * Get upper bound for correct number.
     *
     * @return number that &ge; correct number
     */
    public int getUpperBound() {
        return upperBound;
    }

    /**
     * Check if game has ended.
     *
     * @return true if number was guessed.
     */
    public boolean isGameEnded() {
        return gameEnded;
    }

    /**
     * Analyze user's guess by comparing it to the correct number.
     * Store the guess in array. Modify bounds accordingly.
     *
     * @param guess - user's input
     * @return &lt; sign if guess &lt; correct num, &gt; if guess &gt; correct num
     * otherwise =
     */
    public String processGuess(int guess) {
        guessNums[nGuesses++] = guess;
        if (guess < correctNum) {
            lowerBound = Math.max(lowerBound, guess + 1);
            return "<";
        } else if (guess > correctNum) {
            upperBound = Math.min(upperBound, guess - 1);
            return ">";
        } else {
            lowerBound = upperBound = guess;
            gameEnded = true;
            return "=";
        }
    }

    /**
     * Get numbers that were guessed.
     *
     * @return array that contains user's guesses
     */
    public int[] getGuessNums() {
        return Arrays.copyOfRange(guessNums, 0, nGuesses);
    }
}
