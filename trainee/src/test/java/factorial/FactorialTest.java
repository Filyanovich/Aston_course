package factorial;

import lessons.Main;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FactorialTest {
    @Test
    public void testMainZero() {
        long result = Main.factorial(0);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void testFactorialOne() {
        long result = Main.factorial(1);
        Assert.assertEquals(result, 1);
    }

    @Test
    public void testFactorialTwo() {
        long result = Main.factorial(2);
        Assert.assertEquals(result, 2);
    }

    @Test
    public void testFactorialThree() {
        long result = Main.factorial(3);
        Assert.assertEquals(result, 6);
    }

    @Test
    public void testFactorialFour() {
        long result = Main.factorial(4);
        Assert.assertEquals(result, 24);
    }
}
