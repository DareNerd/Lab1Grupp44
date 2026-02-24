import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;


/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();
    ArrayList<Bilverkstad> carWorkshops = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();
        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        cc.cars.add(new Saab95());
        cc.cars.add(new ScaniaS730());
        cc.cars.add(new Volvo240());

        for(Car car: cc.cars){
            cc.frame.drawPanel.setImageForCar(car, car.getModelName());
        }

        cc.carWorkshops.add(new VolvoWorkshop(8, 300, 300));

        for(Bilverkstad bilverkstad: cc.carWorkshops){
            cc.frame.placeit((int)bilverkstad.getX(), (int)bilverkstad.getY());
        }


        int y = 100;

        for (Car car: cc.cars){
            car.setY(y);
            y += 100;
        }

        // Start the timer
        cc.timer.start();
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

                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();

                double speed = car.getCurrentSpeed();

                if (y >= 510) {
                    car.setCurrentSpeed(0);
                    car.setDirection(Directions.NORTH);
                    car.setCurrentSpeed(speed);
                } else if (y <= 0) {
                    car.setCurrentSpeed(0);
                    car.setDirection(Directions.SOUTH);
                    car.setCurrentSpeed(speed);
                } else if (x >= 700) {
                    car.setCurrentSpeed(0);
                    car.setDirection(Directions.WEST);
                    car.setCurrentSpeed(speed);
                } else if (x <= 0){
                    car.setCurrentSpeed(0);
                    car.setDirection(Directions.EAST);
                    car.setCurrentSpeed(speed);
                }

                if(Math.abs(x - workshopX) <= 10 && Math.abs(y - workshopY) <= 10){
                    if(car instanceof Volvo240){
                        carWorkshops.getFirst().addCar(car);
                        iterator.remove();
                    }
                }
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
}
