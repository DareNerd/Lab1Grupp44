import java.awt.*;

public abstract class Truck extends Car {
    protected double truckBedAngle = 0;

    public Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
}
