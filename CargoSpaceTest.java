import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoSpaceTest {
    CargoSpace tiltableCargoSpace;
    CargoSpace liftableCargoSpace;

    ScaniaS730 testTruck = new ScaniaS730();
    CarTransport testCarTransport = new CarTransport();

    @BeforeEach
    void setup() {
        tiltableCargoSpace = new CargoSpace(true, 70, testTruck);
        liftableCargoSpace = new CargoSpace(true,testCarTransport);
    }

    @Test
    void movingOK() {
        assertTrue(tiltableCargoSpace.movingOK());
        assertTrue(liftableCargoSpace.movingOK());
    }

    @Test
    void movingOkFalse() {
        tiltableCargoSpace.setTruckBedAngle(1);
        assertFalse(tiltableCargoSpace.movingOK());

        liftableCargoSpace.setPlatformLowered(true);
        assertFalse(liftableCargoSpace.movingOK());
    }

    @Test
    void setTruckBedAngle() {
        double expected = 12.34;
        tiltableCargoSpace.setTruckBedAngle(12.34);
        double actual = tiltableCargoSpace.getTruckBedAngle();

        assertEquals(expected, actual);
    }

    @Test
    void getTruckBedAngle() {
        double expected = 56.78;
        tiltableCargoSpace.setTruckBedAngle(56.78);
        double actual = tiltableCargoSpace.getTruckBedAngle();

        assertEquals(expected, actual);
    }

    @Test
    void setIllegalTruckBedAngle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            tiltableCargoSpace.setTruckBedAngle(70.1);
        });

        String expectedMessage = "illegal angle";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void isTiltable() {
        assertTrue(tiltableCargoSpace.isTiltable());
    }

    @Test
    void isNotTiltable() {
        assertFalse(liftableCargoSpace.isTiltable());
    }

    @Test
    void isLiftable() {
        assertTrue(liftableCargoSpace.isLiftable());
    }

    @Test
    void isNotLiftable() {
        assertFalse(tiltableCargoSpace.isLiftable());
    }

    @Test
    void isPlatformLoweredFalse() {
        liftableCargoSpace.setPlatformLowered(false);
        assertFalse(liftableCargoSpace.isPlatformLowered());
    }

    @Test
    void isPlatformLoweredTrue() {
        liftableCargoSpace.setPlatformLowered(true);
        assertTrue(liftableCargoSpace.isPlatformLowered());
    }
}