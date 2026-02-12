import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarTransportTest {
    private CarTransport ct;
    private Volvo240 volvo;
    private ScaniaS730 scania;

    @BeforeEach
    void setup() {
        ct = new CarTransport();
        volvo = new Volvo240();
        scania = new ScaniaS730();
    }

    @Test
    void loadCar() {
        volvo.setX(3);

        ct.lowerPlatform();
        ct.loadCar(volvo);
        int after = ct.getLoadedCars();

        assertEquals(1, after);
    }

    @Test
    void distantCar() {
        volvo.setX(10000);

        ct.lowerPlatform();
        ct.loadCar(volvo);
    }

    @Test
    void loadTruckFail() {
        ct.lowerPlatform();
        ct.loadCar(scania);
        System.out.println(ct.getLoadedCars());
    }

    @Test
    void unLoadCar() {
        volvo.setX(3);

        ct.lowerPlatform();
        ct.loadCar(volvo);
        ct.unLoadCar();

        assertEquals(0, ct.getLoadedCars());
    }

    @Test
    void loadedCarPosition() {
        volvo.setX(3);

        ct.lowerPlatform();
        ct.loadCar(volvo);
        ct.raisePlatform();

        ct.gas(1);
        ct.move();

        assertEquals(ct.getY(), volvo.getY());
    }
}