import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Shot extends Entity {
    boolean dead;
    private final int leben = 60;
    private int aktuellesLeben;

    public Shot(double x_, double y_, double velX_, double velY_, int width_, int height_) {
        super(x_, y_, velX_, velY_, width_, height_);
        dead = false;
        aktuellesLeben = 0;
    }

    public void update() {
        x += velX;
        y += velY;

        borders();
        aktuellesLeben++;
        if (aktuellesLeben == leben) {
            dead = true;
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(255, 255, 255));
        g.fillOval((int) (x + 0.5d), (int) (y + 0.5d), width, height);
        g.setColor(new Color(0, 0, 0));
    }

    public boolean isDead() {
        return dead;
    }
}
