import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint;
    BufferedImage scaniaImage;
    BufferedImage batmobileImage;
    HashMap<Car, BufferedImage> carToImageMap = new HashMap<>();

    void placeit(int x, int y){
        volvoWorkshopPoint = new Point(x, y);
    }
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.orange);
        // Print an error message in case file is not found with a try/catch block
        try {
            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            volvoWorkshopImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/VolvoBrand.jpg"));
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
            batmobileImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/batmobile.jpg"));
        } catch (IOException ex)
        {
            System.out.println("Could not find picture!");
        }


    }
    public void setImageForCar(Car car, String carModel)
    {
        switch (carModel)//cange to enum later
        {
            case "Volvo240":
                carToImageMap.put(car, volvoImage);
                break;
            case "Saab95":
                carToImageMap.put(car, saabImage);
                break;
            case "ScaniaS730":
                carToImageMap.put(car, scaniaImage);
                break;
            case "Batmobile":
                carToImageMap.put(car, batmobileImage);
        }
    }

    public void removeImageForCar(Car car) {
        if (!carToImageMap.isEmpty()) {
            carToImageMap.remove(car);
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Car car: carToImageMap.keySet()){
            if(car == null)return;
            g.drawImage(carToImageMap.get(car),(int)car.getX(), (int)car.getY(), null);
        }
        g.drawImage(volvoWorkshopImage,(int)volvoWorkshopPoint.getX(), (int)volvoWorkshopPoint.getY(), null);
    }
}
