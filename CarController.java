import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // The frame that represents this instance View of the MVC pattern
    private final CarView frame;
    private final CarModel model;

    public CarController(CarView cv, CarModel cm) {
        this.frame = cv;
        this.model = cm;

        init();
    }

    private void init() {
        initActionListeners();
        initChangeListeners();
    }

    private void initActionListeners() {
        frame.gasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.gas(model.getGasAmount());
            }
        });

        frame.brakeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.brake(model.getGasAmount());}
        });

        frame.startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.startAllCars();
            }
        });

        frame.stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.stopAllCars();
            }
        });

        frame.turboOnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOn();
            }
        });

        frame.turboOffButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOff();
            }
        });

        frame.liftBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.liftTruckBed();
            }
        });

        frame.lowerBedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { model.lowerTruckBed(); }
        });

        frame.addCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRandomCar();
                frame.updateCars();
                //model.multicastTimeChange();
            }
        });

        frame.removeCarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.getCars().isEmpty()) {
                    frame.drawPanel.removeImageForCar(model.getCars().getLast());
                    model.removeCar();
                    frame.updateCars();
                    //model.multicastTimeChange();
                }
            }
        });

        frame.turnLeftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turnLeftAllCars();
            }
        });

        frame.turnRightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turnRightAllCars();
            }
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
}
