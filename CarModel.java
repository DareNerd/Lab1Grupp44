import java.util.ArrayList;

public class CarModel {
    final ArrayList<Car> cars = new ArrayList<>();
    final ArrayList<Bilverkstad> carWorkshops = new ArrayList<>();

    private int gasAmount = 0;

    public CarModel() {
        init();
    }

    private void init() {
        cars.add(CarFactory.saab95Factory());
        cars.add(CarFactory.scaniaS730Factory());
        cars.add(CarFactory.volvo240Factory());

        carWorkshops.add(BilverkstadFactory.volvoWorkshopFactory(8, 300, 300));

        int y = 100;
        for (Car car: cars) {
            car.setY(y);
            y += 100;
        }
    }

    public void setGasAmount(int amount) {
        this.gasAmount = amount;
    }

    public int getGasAmount() {
        return this.gasAmount;
    }
}
