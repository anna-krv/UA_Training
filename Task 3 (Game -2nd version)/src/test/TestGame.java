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
        model.setUp(0, 100);
        int lowerBound = model.getLowerBound();
        int upperBound = model.getUpperBound();
        Random rand = new Random();

        while (model.isGameOn()) {
            int n = rand.nextInt(upperBound - lowerBound - 1) + lowerBound + 1;
            model.processGuess(n);
            lowerBound = model.getLowerBound();
            upperBound = model.getUpperBound();
        }
    }


    @Test(timeout = 1000)
    public void testGameDoesEnd() {
        play();
    }

    @Test
    public void testModelNumInBounds() {
        int lower = 1, upper = 99;

        for (int i = 0; i < 10000; i++) {
            model.setUp(lower, upper);
            int n = model.getCorrectNum();
            Assert.assertTrue(n > lower && n < upper);
        }
    }


}
