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

        while (!model.isGameEnded()) {
            int n = rand.nextInt(upperBound - lowerBound - 1) + lowerBound+1;
            model.processGuess(n);
            lowerBound = model.getLowerBound();
            upperBound = model.getUpperBound();
        }
    }


    @Test(timeout = 1000)
    public void testGameDoesEnd() {
        play();
    }


}
