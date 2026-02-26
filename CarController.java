import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;


/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    private CarView frame;
    private CarModel model;

    public CarController(CarView cv, CarModel cm) {
        this.frame = cv;
        this.model = cm;

        init();
    }

    private void init() {
        timer.start();
        initActionListeners();
        initChangeListeners();
    }

    private void initActionListeners() {
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gas(model.getGasAmount());
            }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { brake(model.getGasAmount());}
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startAllCars();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopAllCars();
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                turboOff();
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liftTruckBed();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { lowerTruckBed(); }
        });
    }

    private void initChangeListeners() {
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int gasAmount = (int) ((JSpinner)e.getSource()).getValue();
                model.setGasAmount(gasAmount);
            }
        });
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int workshopX = (int) model.carWorkshops.getFirst().getX();
            int workshopY = (int) model.carWorkshops.getFirst().getY();

            Iterator<Car> iterator = model.cars.iterator();
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
                        model.carWorkshops.getFirst().addCar(car);
                        iterator.remove();
                    }
                }
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : model.cars) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount)/100;
        for (Car car: model.cars){
            car.brake(brake);
        }
    }

    void turboOn() {
        for (Car car: model.cars) {
            if (car instanceof Saab95 saab95) {
                saab95.setTurboOn();
            }
        }
    }

    void turboOff() {
        for (Car car: model.cars) {
            if (car instanceof Saab95 saab95) {
                saab95.setTurboOff();
            }
        }
    }

    void startAllCars() {
        for (Car car: model.cars) {
            car.startEngine();
        }
    }

    void stopAllCars() {
        for (Car car: model.cars) {
            car.stopEngine();
        }
    }

    void liftTruckBed() {
        for (Car car: model.cars) {
            if (car instanceof ScaniaS730 scaniaS730) {
                scaniaS730.setTruckBedAngle(70);
            }
        }
    }

    void lowerTruckBed() {
        for (Car car: model.cars) {
            if (car instanceof ScaniaS730 scaniaS730) {
                scaniaS730.setTruckBedAngle(0);
            }
        }
    }
}
