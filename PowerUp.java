import java.awt.Graphics;

public class PowerUp extends Entity {
    PowerUpTypes type;

    public PowerUp(double x, double y, PowerUpTypes type_) {
        super(x, y, 0, 0, 0, 0);

        type = type_;

        switch (type) {
            case SPEED:
                image = Assets.PowerUpSpeed;
                break;
            case INVINCIBILITY:
                image = Assets.PowerUpInvincibility;
                break;
            case LEBEN:
                image = Assets.PowerUpLeben;
                break;
            case DESTROYALLASTEROIDS:
                image = Assets.PowerUpDestroyAllAsteroids;
                break;
        }

        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }
    }

    public void update() {
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    public PowerUpTypes getType() {
        return type;
    }
}
