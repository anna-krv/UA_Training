package game;

import java.util.Arrays;
import java.util.Random;

public class Model {
    private int correctNum;
    private int[] guessNums;
    private int nGuesses;
    private boolean gameEnded;
    // non - inclusive bounds
    private int lowerBound;
    private int upperBound;

    /**
     * Init basic fields.
     */
    public Model() {
        gameEnded = false;
        nGuesses = 0;
    }

    /**
     * Get number that user should guess.
     * @return secret number
     */
    public int getCorrectNum(){
        return correctNum;
    }

    /**
     * Set bounds and the number that a user should guess.
     * Init array with user's guesses of appropriate size.
     */
    public void setUp(int lowerBound, int upperBound){
        setLowerBound(lowerBound);
        setUpperBound(upperBound);
        correctNum = 1+lowerBound + new Random().nextInt(upperBound - lowerBound -1);
        guessNums = new int[upperBound - lowerBound -1];
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
     * Assume that guess lies in correct bounds.
     *
     * @param guess - user's input
     * @return &lt; sign if guess &lt; correct num, &gt; if guess &gt; correct num
     * otherwise =
     */
    public String processGuess(int guess) {
        guessNums[nGuesses++] = guess;
        if (guess < correctNum) {
            setLowerBound(guess );
            return "<";
        } else if (guess > correctNum) {
            setUpperBound(guess );
            return ">";
        } else {
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

    /**
     * Setter for lower bound
     * @param lowerBound non inclusive bound
     */
    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    /**
     * Setter for upper bound
     * @param upperBound non inclusive bound
     */
    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }
}
