import java.awt.*;
import java.awt.geom.AffineTransform;

public class Asteroid extends Entity {
    private int size;
    private double angularVelocity;
    private double angle;

    public Asteroid
            (double x, double y, double velX, double velY, int size_) {
        super(x, y, velX, velY, 0, 0);

        size = size_;

        angularVelocity = (Math.random() * (2 + 2)) - 2;

        if (size == 3) {
            image = Assets.asteroidGross;
        } else if (size == 2) {
            image = Assets.asteroidMittel;
        } else {
            image = Assets.asteroidKlein;
        }
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        g2d.translate((int) x, (int) y);
        g2d.rotate(Math.toRadians(angle), width / 2, height / 2);

        g.drawImage(image, 0, 0, null);

        g2d.setTransform(originalTransform);
    }

    public void update() {
        x += velX;
        y += velY;

        angle += angularVelocity;

        if (angle > 360) {
            angle -= 360;
        } else if (angle < 0) {
            angle += 360;
        }

        borders();
    }

    public int getSize() {
        return size;
    }
}
