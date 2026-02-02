import java.awt.*;

public class ScaniaS730 extends Truck {

    public ScaniaS730() {
        super(2, 200, Color.magenta, "ScaniaS730");
    }

    public double speedFactor() {
        return getEnginePower() * 0.001;
    }
}
