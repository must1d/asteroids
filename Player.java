import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.Color;

public class Player extends Entity {
    private double angle;
    private double angularVelocity;

    private double acc;

    private boolean forward;
    private boolean rotateLeft;
    private boolean rotateRight;

    private int leben;
    private double startingX, startingY;

    private boolean invincible;
    private boolean visible;

    private boolean dead;

    private final int invincibleTimer = 60 * 2;
    private int actualInvincibleTimer = 0;

    private boolean respawning = false;
    private final int RespawnTimer = 60;
    private int ActualRespawnTimer = 0;

    public Player() {
        super(0, 0, 0, 0, 0, 0);
        image = Assets.player;

        if (image != null) {
            width = image.getWidth();
            height = image.getHeight();
        }

        startingX = Main.WIDTH / 2 - width / 2;
        startingY = Main.HEIGHT / 2 - height / 2;

        x = startingX;
        y = startingY;

        angularVelocity = 4;
        acc = 0.13;

        leben = 3;

        invincible = true;

        dead = false;
    }

    public void render(Graphics g) {
        if (dead) {
            //Todesanimation
        } else {
            if (!visible) {
                return;
            }

            Graphics2D g2d = (Graphics2D) g;
            AffineTransform originalTransform = g2d.getTransform();
            g2d.translate((int) x, (int) y);
            g2d.rotate(Math.toRadians(angle), width / 2, height / 2);

            if (forward) {
                g2d.drawImage(Assets.playerForward, 0, 0, null);
            } else {
                g2d.drawImage(image, 0, 0, null);
            }

            g2d.setTransform(originalTransform);
        }
    }

    public void update() {
        x += velX;
        y += velY;

        if (angle > 360) {
            angle -= 360;
        } else if (angle < 0) {
            angle += 360;
        }

        if (forward) {
            velX += acc * Math.sin(Math.toRadians(angle));
            velY -= acc * Math.cos(Math.toRadians(angle));
        }
        if (rotateRight) {
            angle += angularVelocity;
        }
        if (rotateLeft) {
            angle -= angularVelocity;
        }

        //Friction

        velX *= 0.99;
        velY *= 0.99;

        borders();

        if (invincible) {
            actualInvincibleTimer++;
            if (actualInvincibleTimer % 15 == 0) {
                visible = !visible;
            }
        }
        if (actualInvincibleTimer >= invincibleTimer) {
            invincible = false;
            visible = true;
            actualInvincibleTimer = 0;
        }
        if (respawning) {
            ActualRespawnTimer += 1;
            if (ActualRespawnTimer == RespawnTimer) {
                ActualRespawnTimer = 0;
                respawning = false;
                respawn();
            }
        }
    }

    public double getAngle() {
        return angle;
    }

    public double xSpitze() {
        double x_ = x + width / 2;
        x_ += 1 / 2 * width * Math.sin(Math.toRadians(angle));
        return x_;
    }

    public double ySpitze() {
        double y_ = y + height / 2;
        y_ -= 1 / 2 * width * Math.sin(Math.toRadians(angle));
        return y_;
    }

    public void addLeben() {
        leben++;
    }

    public boolean invincible() {
        return invincible;
    }

    public void death() {
        leben--;
        dead = true;
        respawning = true;
    }

    private void respawn() {
        x = startingX;
        y = startingY;

        invincible = true;
        angle = 0;

        velX = 0;
        velY = 0;

        dead = false;
    }

    public int returnLeben() {
        return leben;
    }

    public boolean heartsLeft() {
        if (leben > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isRespawning() {
        return respawning;
    }

    public void setForward(boolean b) {
        forward = b;
    }

    public void setRotateLeft(boolean b) {
        rotateLeft = b;
    }

    public void setRotateRight(boolean b) {
        rotateRight = b;
    }

    public void setInvincible() {
        invincible = true;
        actualInvincibleTimer = 0;
    }

    public void force() {
        velX += 60 * acc * Math.sin(Math.toRadians(angle));
        velY -= 60 * acc * Math.cos(Math.toRadians(angle));
    }
}
