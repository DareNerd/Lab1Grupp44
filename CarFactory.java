public class CarFactory {

    public static Volvo240 volvo240Factory() {
        return new Volvo240();
    }

    public static Saab95 saab95Factory() {
        return new Saab95();
    }

    public static ScaniaS730 scaniaS730Factory() {
        return new ScaniaS730();
    }

    public static CarTransport carTransportFactory() {
        return new CarTransport();
    }

    public static Batmobile batmobileFactory() { return new Batmobile(); }
}
