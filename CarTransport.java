import java.awt.*;

public class CarTransport extends Truck {

    public CarTransport() {
        super(2, 3000, Color.yellow, "CarTransport");
    }

    public double speedFactor() {
        return getEnginePower() * 0.0001;
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
