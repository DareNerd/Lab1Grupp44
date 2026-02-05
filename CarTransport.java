import java.awt.*;
import java.util.ArrayList;

public class CarTransport extends Truck {
    private final int MAX_CAPACITY = 8;
    private final ArrayList<Car> loadedCars = new ArrayList<>();

    public CarTransport() {
        super(2, 3000, Color.yellow, "CarTransport");
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
        } else {
            loadedCars.add(car);
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }

    public void unLoadCar() {
        Car lastCar = loadedCars.getLast();
        lastCar.setX(this.getX() - 1);
        loadedCars.removeLast();
    }

    public void move() {
        super.move();

        for (Car car : loadedCars) {
            car.setX(this.getX());
            car.setY(this.getY());
        }
    }

    // TODO: fixa truck bed angle så den tar int 0 eller 1
    public void setTruckBedAngle(int angle) {
        if (this.getCurrentSpeed() != 0) {
            throw new RuntimeException("you are driving!");
        } else if (angle < 0 || angle > 1) {
            throw new IllegalArgumentException("illegal angle");
        }
        this.truckBedAngle = angle;
    }
}
