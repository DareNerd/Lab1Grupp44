import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaS730Test {
    private ScaniaS730 test;

    @BeforeEach
    void setup() {
        test = new ScaniaS730();
    }

    @Test
    void setTruckBedAngle() {
        test.setTruckBedAngle(66.6);
        double expected = 66.6;

        assertEquals(expected, test.getTruckBedAngle());
    }

    @Test
    void setIllegalTruckBedAngle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            test.setTruckBedAngle(666.666);
        });

        String expected = "illegal angle";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void moveException() {
        test.setTruckBedAngle(42.0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            test.move();
        });

        String expectedMessage = "your truck bed is open!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}