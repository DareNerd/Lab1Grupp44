public class App {
    static void main(String[] args) {
        CarModel model = new CarModel();
        CarView view = new CarView(model);
        new CarController(view, model);
    }
}
