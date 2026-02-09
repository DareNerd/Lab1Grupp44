import java.awt.*;

public class ScaniaS730 extends Truck {

    private final CargoSpace truckBed;

    public ScaniaS730() {
        super(2, 200, Color.magenta, "ScaniaS730");
        truckBed = new CargoSpace(true, 70, this);
    }

    public double speedFactor() {
        return getEnginePower() * 0.001;
    }

    @Override
    public void gas(double amount) {
        if (!truckBed.movingOK()) {
            throw new RuntimeException("your truck bed is open!");
        }
        super.gas(amount);
    }

    @Override
    public void move() {
        if (!truckBed.movingOK()) {
            throw new RuntimeException("your truck bed is open!");
        }
        super.move();
    }

    public void setTruckBedAngle(double angle) {
        if (getCurrentSpeed() != 0) {
            throw new RuntimeException("can't change angle while you are driving!");
        }
        truckBed.setTruckBedAngle(angle);
    }
}
