import java.awt.*;

public abstract class Truck extends Car {
    protected double truckBedAngle = 0;

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    public void setTruckBedAngle(double angle) {
        if (this.getCurrentSpeed() != 0) {
            throw new RuntimeException("you are driving!");
        } else if (angle < 0 || angle > 70) {
            throw new IllegalArgumentException("illegal angle");
        }
        this.truckBedAngle = angle;
    }

    public void move() {
        if (this.truckBedAngle != 0) {
            throw new RuntimeException("your truck bed is up!");
        }
        super.move();
    }
}
