package Test;

import Calculation.Arithmetics;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

public class TestArithmetics {
    private final double DELTA = 1e-12;
    private static Arithmetics a;

    @Rule
    public final ExpectedException exp = ExpectedException.none();

    @Rule
    public Timeout time = new Timeout(1000);

    @BeforeClass
    public static void runT() {
        a = new Arithmetics();
    }

    @Test
    public void testAdd() {
        double res = a.add(3, 7);
        Assert.assertEquals(10., res, DELTA);

    }

    @Test
    public void testMult() {
        double res = a.mult(3, 7);
        Assert.assertEquals(21., res, DELTA);

    }

    @Ignore
    @Test
    public void testDeduct() {
        double res = a.deduct(3, 7);
        Assert.assertEquals(-4., res, DELTA);

    }

    @Test
    public void testDiv() {
        double res = a.div(10, 5);
        Assert.assertEquals(2., res, DELTA);

    }

    //@Test(expected = ArithmeticException.class)
    @Test
    public void testDivException() {
        exp.expect(ArithmeticException.class);
        a.div(10., 0.);
    }

    @Test
    public void testN() {
        while (true) {
        }
    }
}
