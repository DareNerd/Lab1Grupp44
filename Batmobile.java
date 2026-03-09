import java.awt.*;

public class Batmobile extends Car {
    private boolean turboOn = false;
    private final static double trimFactor = 1.25;

    public Batmobile() {
        super(1, 250, Color.black, "Batmobile");
    }

    public void setTurboOn() { turboOn = true; }

    public void setTurboOff() { turboOn = false; }

    public double speedFactor() {
        double turbo = 1;
        if (turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * trimFactor * turbo;
    }
}

/*
antal steg när man vill lägga till en ny bil:

1. skapa en ny klass för bilen
2. skapa en ny factory för bilen
3. lägg till bilden på bilen i drawpanel (BufferedImage)
4. lägg till bilden på bilen i try/catch-blocket i drawpanel
5. fixa så bilen och bilden hamnar i haschmapen (drawpanel)
6. lägg till bilen i "addRandomCar" i CarModel samt uppdatera taket för randomInt
(7. skapa en bilverkstad för den bilmodellen)

 */
