import java.util.ArrayList;

public class Bilverkstad<A> {
    private final int maxCapacity;
    private final ArrayList<A> carArrayList = new ArrayList<>();

    public Bilverkstad(int capacity) {
        this.maxCapacity = capacity;
    }
    void addCar(A car) {
        carArrayList.add(car);
    }

    void removeCar(A car) {
        if (!carArrayList.remove(car)) {
            System.out.println("Du har gått till fel bilverkstad!");
        } else {
            carArrayList.remove(car);
        }
    }
}
