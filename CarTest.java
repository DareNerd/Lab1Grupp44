import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {
    private Car car;

    @BeforeEach
    void setup() {
        car = new Volvo240();
    }

    @Test
    void gasIncreasesSpeed() {
        car.startEngine();

        double speedBefore = car.getCurrentSpeed();

        car.gas(1.0);

        assertTrue(car.getCurrentSpeed() > speedBefore);
    }

    @Test
    void negativeAmountOnGas() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            car.gas(-1.0);
        });

        String expectedMessage = "illegal amount";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void brakeDecreasesSpeed() {
        car.startEngine();
        car.gas(1.0);

        double speedBefore = car.getCurrentSpeed();

        car.brake(0.5);

        assertTrue(car.getCurrentSpeed() < speedBefore);
    }

    @Test
    void speedNeverExceedsEnginePower() {
        car.startEngine();

        for (int i = 0; i < 200; i++) {
            car.gas(1.0);
        }

        assertEquals(car.getEnginePower(), car.getCurrentSpeed());
    }

    @Test
    void speedNeverDropsBelowZero() {
        car.startEngine();

        for (int i = 0; i < 200; i++) {
            car.brake(1.0);
        }

        assertEquals(0, car.getCurrentSpeed());
    }

    @Test
    void saabTurboIncreasesAcceleration() {
        Saab95 car1 = new Saab95();
        Saab95 car2 = new Saab95();

        car1.startEngine();
        car2.startEngine();
        car2.setTurboOn();

        for (int i = 0; i < 50; i++) {
            car1.gas(1.0);
            car2.gas(1.0);
        }

        assertTrue(car2.getCurrentSpeed() > car1.getCurrentSpeed());
    }
}