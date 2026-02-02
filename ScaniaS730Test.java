import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScaniaS730Test {

    @Test
    void setTruckBedAngle() {
    }

    @Test
    void move() {
        ScaniaS730 test = new ScaniaS730();
        test.setTruckBedAngle(42.0);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            test.move();
        });

        String expectedMessage = "your truck bed is up!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}