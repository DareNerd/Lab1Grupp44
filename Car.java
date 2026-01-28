import java.awt.*;

public abstract class Car {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    private double x = 0;
    private double y = 0;

    private String direction = "north";

    public Car() {}

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    public void move() {
        if(this.direction.equals("north")) {
            this.y += getCurrentSpeed();
        } else if (this.direction.equals("south")) {
            this.y -= getCurrentSpeed();
        } else if (this.direction.equals("east")) {
            this.x += getCurrentSpeed();
        } else {
            this.x -= getCurrentSpeed();
        }
    }

    public void turnLeft() {
        if(this.direction.equals("north")) {
            this.direction = "west";
        } else if (this.direction.equals("south")) {
            this.direction = "east";
        } else if (this.direction.equals("east")) {
            this.direction = "north";
        } else {
            this.direction = "south";
        }
    }

    public void turnRight() {
        if(this.direction.equals("north")) {
            this.direction = "east";
        } else if (this.direction.equals("south")) {
            this.direction = "west";
        } else if (this.direction.equals("east")) {
            this.direction = "south";
        } else {
            this.direction = "north";
        }
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("illegal amount");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if (0 <= amount && amount <= 1) {
            decrementSpeed(amount);
        } else {
            throw new IllegalArgumentException("illegal amount");
        }
    }

    public abstract double speedFactor();

    public abstract void incrementSpeed(double amount);

    public abstract void decrementSpeed(double amount);

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    public int getNrDoors() {
        return nrDoors;
    }

    protected void setNrDoors(int nrDoors) {
        this.nrDoors = nrDoors;
    }

    public double getEnginePower() {
        return enginePower;
    }

    protected void setEnginePower(double enginePower) {
        this.enginePower = enginePower;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double speed) {
        if (speed > this.enginePower) {
            throw new IllegalArgumentException("speed too high");
        } else if (speed < 0) {
            throw new IllegalArgumentException("speed can't be negative");
        }
        this.currentSpeed = speed;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getModelName() {
        return modelName;
    }

    protected void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
