import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    @Test
    void gasIncreasesSpeed() {
        Car car = new Volvo240();
        car.startEngine();

        double speedBefore = car.getCurrentSpeed();

        car.gas(1.0);

        assertTrue(car.getCurrentSpeed() > speedBefore);
    }

    @Test
    void negativeAmountOnGas() {
        Car car = new Saab95();


        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            car.gas(-1.0);
        });

        String expectedMessage = "illegal amount";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void brakeDecreasesSpeed() {
        Car car = new Saab95();
        car.startEngine();
        car.gas(1.0);

        double speedBefore = car.getCurrentSpeed();

        car.brake(0.5);

        assertTrue(car.getCurrentSpeed() < speedBefore);
    }

    @Test
    void speedNeverExceedsEnginePower() {
        Car car = new Saab95();
        car.startEngine();

        for (int i = 0; i < 200; i++) {
            car.gas(1.0);
        }

        assertEquals(car.getEnginePower(), car.getCurrentSpeed());
    }

    @Test
    void speedNeverDropsBelowZero() {
        Car car = new Volvo240();
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