import java.awt.image.BufferedImage;

public class CarGameImage {
    private final BufferedImage image;
    private final String type;
    private int x = 0;
    private int y = 0;

    public CarGameImage(BufferedImage image, String type, int x, int y) {
        this.image = image;
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public String getType(){return type;}
    public BufferedImage getImage(){return image;}

    public int getX(){return x;}
    public void setX(int x) {this.x = x;}

    public int getY(){return y;}
    public void setY(int y) {this.y = y;}
}
