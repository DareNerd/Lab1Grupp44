import java.util.ArrayList;

public class Bilverkstad<A extends Car> {
    private final int maxCapacity;
    private final ArrayList<A> carArrayList = new ArrayList<>();
    private int x = 0;
    private int y = 0;

    public Bilverkstad(int capacity, int x, int y) {
        this.maxCapacity = capacity;
        this.x = x;
        this.y = y;
    }

    public Bilverkstad(int capacity){
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

    public ArrayList<A> getCarArrayList() {
        return carArrayList;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getCapacity() {
        return carArrayList.size();
    }

}
