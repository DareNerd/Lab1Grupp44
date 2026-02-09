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

        String expectedMessage = "your truck bed is open!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void moveAgain() {
        ScaniaS730 test = new ScaniaS730();
        System.out.println(test.truckBedAngle);
        test.setCurrentSpeed(10);
        test.gas(0.5);
    }
}