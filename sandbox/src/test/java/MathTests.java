import org.junit.jupiter.api.Test;

public class MathTests {
    @Test
    void testDividByZero() {
        var x = 1;
        var y = 1;
        var z = x / y;
        System.out.println(z);
    }
}
