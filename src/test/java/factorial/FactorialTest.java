package factorial;

import lessons.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {
    @Test
    public void testFactorial() {
        assertEquals(1, Main.factorial(0));
        assertEquals(1, Main.factorial(1));
        assertEquals(2, Main.factorial(2));
        assertEquals(6, Main.factorial(3));
        assertEquals(24, Main.factorial(4));
    }

}
