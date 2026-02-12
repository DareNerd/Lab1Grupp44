import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BilverkstadTest {

    @Test
    void addCar() {
        Volvo240 volvo = new Volvo240();
        Saab95 saab = new Saab95();
        Bilverkstad<Volvo240> b = new Bilverkstad<>(20);
        b.addCar(volvo);
        // b.addCar(saab);
    }

    @Test
    void testMaxCapacity() {
        Car car1 = new Volvo240();
        Car car2 = new Saab95();
        Car car3 = new Volvo240();

        Bilverkstad<Car> b = new Bilverkstad<>(2);

        b.addCar(car1);
        b.addCar(car2);
        //b.addCar(car3);
    }

    @Test
    void removeCar() {
        Bilverkstad<Volvo240> b = new Bilverkstad<>(10);
        Volvo240 v1 = new Volvo240();
        Volvo240 v2 = new Volvo240();

        b.addCar(v1);
        b.addCar(v2);
        b.removeCar(v1);

        int expected = 1;
        int actual = b.getCapacity();

        assertEquals(expected, actual);
    }

    @Test
    void removeNonExistingCar() {
        Bilverkstad<Volvo240> b = new Bilverkstad<>(10);
        Volvo240 v1 = new Volvo240();
        Volvo240 v2 = new Volvo240();

        b.addCar(v1);

        b.removeCar(v2);
    }
}