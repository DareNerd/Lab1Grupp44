import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage volvoWorkshopImage;
    Point volvoWorkshopPoint;
    BufferedImage scaniaImage;
    HashMap<Integer, BufferedImage> carToImageMap = new HashMap<>();
    HashMap<Integer, Point> carToPointMap = new HashMap<>();


    // TODO: Make this general for all cars
    void moveit(int hashcode, int x, int y){
        carToPointMap.put(hashcode, new Point(x,y));
    }

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
        } catch (IOException ex)
        {
            System.out.println("Could not find picture!");
        }


    }
    public void setImageForCar(int hashCode, String carModel)
    {
        switch (carModel)//cange to enum later
        {
            case "Volvo240":
                carToImageMap.put(hashCode, volvoImage);
                break;
            case "Saab95":
                carToImageMap.put(hashCode, saabImage);
                break;
            case "ScaniaS730":
                carToImageMap.put(hashCode, scaniaImage);
                break;

        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Integer hashCode: carToImageMap.keySet()){
            if(hashCode == null)return;
            g.drawImage(carToImageMap.get(hashCode),(int)carToPointMap.get(hashCode).getX(), (int)carToPointMap.get(hashCode).getY(), null);
        }
        g.drawImage(volvoWorkshopImage,(int)volvoWorkshopPoint.getX(), (int)volvoWorkshopPoint.getY(), null);
    }
}
