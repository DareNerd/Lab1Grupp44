import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Truck {
    private final CargoSpace truckBed;
    private final int MAX_CAPACITY = 8;
    private final ArrayList<Car> loadedCars = new ArrayList<>();

    public CarTransport() {
        super(2, 3000, Color.yellow, "CarTransport");
        truckBed = new CargoSpace(true, this);
    }

    public double speedFactor() {
        return getEnginePower() * 0.0001;
    }

    public void loadCar(Car car) {
        double distance;

        distance = Math.sqrt(Math.pow(this.getX() - car.getX(), 2.0)
                + Math.pow(this.getY() - car.getY(), 2.0));

        if (loadedCars.size() == MAX_CAPACITY) {
            System.out.println("Max capacity!");
        } else if (distance > 5.0) {
            System.out.println("The car is too far away!");
        } else if (!truckBed.isPlatformLowered()) {
            System.out.println("Platform not lowered!");
        } else if (car.getClass().equals(Truck.class)) { // TODO: KOLLA OM DET ÄR EN LASTBIL ELLER EJ
            System.out.println("För stor!");
        }
        else {
            loadedCars.add(car);
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }

    public void unLoadCar() {
        if (!truckBed.isPlatformLowered()) {
            System.out.println("Car cannot fly!");
        } else {
            Car lastCar = loadedCars.getLast();
            lastCar.setX(this.getX() - 1);
            loadedCars.removeLast();
        }
    }

    public void move() {
        if (!truckBed.movingOK()) {
            System.out.println("Moving not OK!");
        } else {
            super.move();

            for (Car car : loadedCars) {
                car.setX(this.getX());
                car.setY(this.getY());
            }
        }
    }
}
