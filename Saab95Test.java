import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {
    private Saab95 test;

    @BeforeEach
    void setup() {
        test = new Saab95();
    }

    @Test
    void setTurboOn() {
        double before = test.speedFactor();
        test.setTurboOn();
        double after = test.speedFactor();
        assertTrue(before < after);
    }

    @Test
    void setTurboOff() {
        test.setTurboOn();
        double before = test.speedFactor();
        test.setTurboOff();
        double after = test.speedFactor();
        assertTrue(before > after);
    }

    @Test
    void speedFactorWithoutTurbo() {
        // enginePower * 0.01 * turbo
        double expected = 125 * 0.01 * 1;
        assertEquals(expected, test.speedFactor());
    }

    @Test
    void speedFactorWithTurbo() {
        // turbo = 1.3
        test.setTurboOn();
        double expected = 125 * 0.01 * 1.3;
        assertEquals(expected, test.speedFactor());
    }
}