package test;

import game.Model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class TestGame {
    private static Model model;

    @Before
    public void runInit() {
        model = new Model();
    }

    public void play() {
        int lowerBound = model.getLowerBound();
        int upperBound = model.getUpperBound();
        Random rand = new Random();

        while (!model.isGameEnded()) {
            int n = rand.nextInt(upperBound - lowerBound + 1) + lowerBound;
            model.processGuess(n);
            lowerBound = model.getLowerBound();
            upperBound = model.getUpperBound();
        }
    }

    @Test
    public void testModelTooLowInput() {
        int bound = model.getLowerBound();
        Assert.assertFalse(model.isValidGuess(bound - 1));
    }

    @Test
    public void testModelTooHighInput() {
        int bound = model.getUpperBound();
        Assert.assertFalse(model.isValidGuess(bound + 1));
    }

    @Test
    public void testModelValidInput() {
        int lowerBound = model.getLowerBound();
        int upperBound = model.getUpperBound();
        Assert.assertTrue(model.isValidGuess((lowerBound + upperBound) / 2));
    }

    @Test
    public void testBounds() {
        Assert.assertTrue(model.getLowerBound() < model.getUpperBound());
    }

    @Test(timeout = 1000)
    public void testGameDoesEnd() {
        play();
    }

    @Test
    public void testGameEndBounds() {
        play();
        Assert.assertEquals(model.getLowerBound(), model.getUpperBound());
    }

}
