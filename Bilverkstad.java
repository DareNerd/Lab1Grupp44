import java.util.ArrayList;

public class Bilverkstad<A> {
    private final int maxCapacity;
    private final ArrayList<A> carArrayList = new ArrayList<>();

    public Bilverkstad(int capacity) {
        this.maxCapacity = capacity;
    }


    public void addCar(A car) {
        if (carArrayList.size() >= maxCapacity) {
            System.out.println("Bilverkstaden är full!");
        } else {
            carArrayList.add(car);
        }
    }

    public void removeCar(A car) {
        if (!carArrayList.remove(car)) {
            System.out.println("Du har gått till fel bilverkstad!");
        } else {
            carArrayList.remove(car);
        }
    }

    public int getCapacity() {
        return carArrayList.size();
    }
}
