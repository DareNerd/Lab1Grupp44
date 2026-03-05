import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class CarModel {
    private final ArrayList<Car> cars = new ArrayList<>();
    private final ArrayList<Bilverkstad> carWorkshops = new ArrayList<>();

    private int gasAmount = 0;

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private final Timer timer = new Timer(delay, new TimerListener());

    private int newCarYPosition = 10;

    public CarModel() {
        init();
    }

    private void init() {
        cars.add(CarFactory.saab95Factory());
        cars.add(CarFactory.scaniaS730Factory());
        cars.add(CarFactory.volvo240Factory());

        carWorkshops.add(BilverkstadFactory.volvoWorkshopFactory(8, 300, 300));


        for (Car car: cars) {
            car.setY(newCarYPosition);
            newCarYPosition += 80;
        }
        this.timer.start();
    }

    public void setGasAmount(int amount) {
        this.gasAmount = amount;
    }

    public int getGasAmount() {
        return this.gasAmount;
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public ArrayList<Bilverkstad> getCarWorkshops() {
        return carWorkshops;
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int workshopX = (int) carWorkshops.getFirst().getX();
            int workshopY = (int) carWorkshops.getFirst().getY();

            Iterator<Car> iterator = cars.iterator();
            while (iterator.hasNext()) {
                Car car = iterator.next();
                car.move();

                int y = (int) car.getY();
                int x = (int) car.getX();

                if (y >= 510) {
                    car.reverseDirection();
                } else if (y <= 0) {
                    car.reverseDirection();
                } else if (x >= 700) {
                    car.reverseDirection();
                } else if (x <= 0){
                    car.reverseDirection();
                }

                if(Math.abs(x - workshopX) <= 30 && Math.abs(y - workshopY) <= 30){
                    if(car instanceof Volvo240){
                        carWorkshops.getFirst().addCar(car);
                        iterator.remove();
                    }
                }

                multicastTimeChange();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount)/100;
        for (Car car: cars){
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Car car: cars) {
            if (car instanceof Saab95 saab95) {
                saab95.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car: cars) {
            if (car instanceof Saab95 saab95) {
                saab95.setTurboOff();
            }
        }
    }

    void startAllCars() {
        for (Car car: cars) {
            car.startEngine();
        }
    }

    void stopAllCars() {
        for (Car car: cars) {
            car.stopEngine();
        }
    }

    void liftTruckBed() {
        for (Car car: cars) {
            if (car instanceof ScaniaS730 scaniaS730) {
                scaniaS730.setTruckBedAngle(70);
            }
        }
    }

    void lowerTruckBed() {
        for (Car car: cars) {
            if (car instanceof ScaniaS730 scaniaS730) {
                scaniaS730.setTruckBedAngle(0);
            }
        }
    }

    public void addRandomCar() {
        if (cars.size() < 7) {
            Random random = new Random();
            int randomInt = random.nextInt(3);
            Car newCar;
            switch (randomInt) {
                case 0: newCar = CarFactory.saab95Factory(); break;
                case 1: newCar = CarFactory.scaniaS730Factory(); break;
                default: newCar = CarFactory.volvo240Factory(); break;
            }
            newCar.setY(newCarYPosition);
            newCarYPosition += 80;
            cars.add(newCar);
        }
    }

    public void removeCar() {
        if (!cars.isEmpty()) {
            cars.removeLast();
            newCarYPosition -= 80;
        }
    }

    public void turnLeftAllCars() {
        for (Car car: cars) {
            car.turnLeft();
        }
    }

    public void turnRightAllCars() {
        for (Car car: cars) {
            car.turnRight();
        }
    }

    private final List<TimeObserver> observers = new ArrayList<>();

    public void addObserver(TimeObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(TimeObserver observer) {
        observers.remove(observer);
    }

    public void multicastTimeChange() {
        for (TimeObserver observer : observers) {
            observer.actOnTimeChange();
        }
    }
}
