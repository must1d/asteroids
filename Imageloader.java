import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Imageloader {
    public Imageloader() {
    }

    public BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Imageloader.class.getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}

