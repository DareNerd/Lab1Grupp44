import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represents the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage volvoWorkshopImage;
    BufferedImage scaniaImage;

    private static final ArrayList<CarGameImage> gameImages = new ArrayList<>();

    // TODO: Make this general for all cars
    void moveit(Car car){
        for (CarGameImage carGameImage : gameImages){
            if (carGameImage.getType().equals(car.getModelName())){
                carGameImage.setX((int)car.getX());
                carGameImage.setY((int)car.getY());
            }
        }

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
            ex.printStackTrace();
        }
        gameImages.add(new CarGameImage(volvoImage, "Volvo240", 0 , 0));
        gameImages.add(new CarGameImage(saabImage, "Saab95", 0 , 0));
        gameImages.add(new CarGameImage(scaniaImage, "ScaniaS730", 0 , 0));
        gameImages.add(new CarGameImage(volvoWorkshopImage, "volvoWorkshop", 300 , 300));
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (CarGameImage carGameImage : gameImages){
            g.drawImage(carGameImage.getImage(),carGameImage.getX(), carGameImage.getY(), null);
        }
    }
}
