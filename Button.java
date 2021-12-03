import java.awt.Rectangle;
import java.awt.Graphics;

import java.awt.image.BufferedImage;

public class Button {
    private int x, y;
    private int width, height;

    private boolean pressed;
    //private boolean mouseOnButton;

    private Rectangle box;

    private BufferedImage image;
    private BufferedImage imagePressed;

    public Button(int x_, int y_, BufferedImage image_, BufferedImage imagePressed_) {
        x = x_;
        y = y_;

        image = image_;
        imagePressed = imagePressed_;
        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }

        pressed = false;

        box = new Rectangle(x, y, width, height);
    }

    public Button(int y_, BufferedImage image_, BufferedImage imagePressed_) {
        image = image_;
        imagePressed = imagePressed_;

        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }

        x = Main.WIDTH / 2 - width / 2;
        y = y_;

        box = new Rectangle(x, y, width, height);
    }

    public void update() {
    }

    public void render(Graphics g) {
        if (pressed) {
            g.drawImage(imagePressed, x, y, null);
        } else {
            g.drawImage(image, x, y, null);
        }
    }

    public boolean onButton(int x, int y) {
        return box.contains(x, y);
    }

    public void pressed() {
        pressed = true;
    }

    public void notPressed() {
        pressed = false;
    }

    public boolean isPressed() {
        return pressed;
    }

    public int getHeight() {
        return height;
    }
}
