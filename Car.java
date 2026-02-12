import java.awt.*;

public abstract class Car implements Movable {
    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    private double x = 0;
    private double y = 0;

    private Directions direction = Directions.EAST;

    public Car() {}

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        stopEngine();
    }

    public void move() {
        if(getDirection() == Directions.NORTH) {
            this.y -= getCurrentSpeed();
        } else if (getDirection() == Directions.SOUTH) {
            this.y += getCurrentSpeed();
        } else if (getDirection() == Directions.EAST) {
            this.x += getCurrentSpeed();
        } else if (getDirection() == Directions.WEST) {
            this.x -= getCurrentSpeed();
        }
    }

    public void turnLeft() {
        if(getDirection() == Directions.NORTH) {
            setDirection(Directions.WEST);
        } else if (getDirection() == Directions.SOUTH) {
            setDirection(Directions.EAST);
        } else if (getDirection() == Directions.EAST) {
            setDirection(Directions.NORTH);
        } else if (getDirection() == Directions.WEST) {
            setDirection(Directions.SOUTH);
        }
    }

    public void turnRight() {
        if(getDirection() == Directions.NORTH) {
            setDirection(Directions.EAST);
        } else if (getDirection() == Directions.SOUTH) {
            setDirection(Directions.WEST);
        } else if (getDirection() == Directions.EAST) {
            setDirection(Directions.SOUTH);
        } else if (getDirection() == Directions.WEST) {
            setDirection(Directions.NORTH);
        }
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("illegal amount");
        }
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("illegal amount");
        }
        decrementSpeed(amount);
    }

    public abstract double speedFactor();

    public void incrementSpeed(double amount){
        setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
    }

    public void decrementSpeed(double amount){
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }

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

    public Directions getDirection() {
        return direction;
    }

    public void setDirection(Directions direction) {
        this.direction = direction;
    }
}
