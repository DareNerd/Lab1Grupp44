import java.awt.*;

public class Volvo240 extends Car {

    private final static double trimFactor = 1.25;
    
    public Volvo240(){
        super(4, 100, Color.black, "Volvo240");
    }

    public double speedFactor(){
        return getEnginePower() * 0.01 * trimFactor;
    }

    public void incrementSpeed(double amount){
	    setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount,getEnginePower()));
    }

    public void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount,0));
    }
}
