import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        super(4, 100, Color.black, "Volvo240");
    }

    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    static void main() {
        Car car = new Volvo240();
        car.gas(-1.0);
        System.out.println(car.getCurrentSpeed());
    }
}
