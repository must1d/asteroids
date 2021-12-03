import java.awt.Graphics;

public class Enemy extends Entity {
    public Enemy(double x, double y) {
        super(x, y, 0, 0, 0, 0);

        image = Assets.player;

        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
    }

    public void update() {
    }

    public void render(Graphics g) {
    }
}
