package game;

import java.util.Arrays;
import java.util.Random;

public class Model {
    private int correctNum;
    private int[] guessNums;
    private int nGuesses;
    private boolean gameOn;
    // non - inclusive bounds
    private int lowerBound;
    private int upperBound;

    public Model() {
        gameOn = true;
        nGuesses = 0;
    }

    /**
     * Set bounds and the number that a user should guess.
     * Init array with user's guesses of appropriate size.
     */
    public void setUp(int lowerBound, int upperBound) {
        setLowerBound(lowerBound);
        setUpperBound(upperBound);
        correctNum = 1 + lowerBound + new Random().nextInt(upperBound - lowerBound - 1);
        guessNums = new int[upperBound - lowerBound - 1];
    }

    /**
     * Analyze user's guess by comparing it to the correct number.
     * Store the guess in array. Modify bounds accordingly.
     * Assume that guess lies in correct bounds.
     *
     * @param guess - user's input
     * @return &lt; sign if guess &lt; correct num, &gt; if guess &gt; correct num
     * otherwise =
     */
    public String processGuess(int guess) {
        guessNums[nGuesses++] = guess;
        if (guess < correctNum) {
            setLowerBound(guess);
            return "<";
        } else if (guess > correctNum) {
            setUpperBound(guess);
            return ">";
        } else {
            gameOn = false;
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

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public int getCorrectNum() {
        return correctNum;
    }

}
