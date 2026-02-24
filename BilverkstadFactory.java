public class BilverkstadFactory {


    public static Bilverkstad bilverkstadFactory(int maxCapacity, int x, int y) {
        return new Bilverkstad(maxCapacity, x, y);
    }

    public static VolvoWorkshop volvoWorkshopFactory(int maxCapacity, int x, int y) {
        return new VolvoWorkshop(maxCapacity, x, y);
    }
}
