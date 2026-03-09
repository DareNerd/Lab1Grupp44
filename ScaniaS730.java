import java.awt.*;

public class ScaniaS730 extends Truck {

    private final CargoSpace truckBed;

    public ScaniaS730() {
        super(2, 200, Color.magenta, "ScaniaS730");
        truckBed = new CargoSpace(true, 70);
    }

    public double speedFactor() {
        return getEnginePower() * 0.005;
    }

    @Override
    public void gas(double amount) {
        if (truckBed.getTruckBedAngle() > 0) {
            System.out.println("Your truck bed is open! (gas)");
        }
        super.gas(amount);
    }

    @Override
    public void move() {
        if (truckBed.getTruckBedAngle() > 0) {
            System.out.println("Truckbed is open!");
        } else {
            super.move();
        }
    }

    public void setTruckBedAngle(double angle) {
        if (getCurrentSpeed() != 0) {
            System.out.println("Can't raise your truck bed while you are driving!");
        } else{
            truckBed.setTruckBedAngle(angle);
        }
    }

    public double getTruckBedAngle() {
        return truckBed.getTruckBedAngle();
    }
}
