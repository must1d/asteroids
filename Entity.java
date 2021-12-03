import java.awt.Graphics;
import java.awt.Rectangle;

import java.awt.image.BufferedImage;

public abstract class Entity {
    protected double x, y;
    protected double velX, velY;
    protected int width, height;

    protected BufferedImage image;

    protected Rectangle hitbox;

    Entity(double x_, double y_, double velX_, double velY_, int width_, int height_) {
        x = x_;
        y = y_;
        velX = velX_;
        velY = velY_;
        width = width_;
        height = height_;
    }

    public abstract void render(Graphics g);

    public abstract void update();

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getVel() {
        return Math.sqrt((Math.pow(velX, 2)) + (Math.pow(velY, 2)));
    }

    public void Kraft(double x, double y) {
        velX += x;
        velY += y;
    }

    protected void borders() {
        if (x + width < 0) {
            x = Main.WIDTH;
        } else if (x > Main.WIDTH) {
            x = -width;
        }
        if (y + height < 0) {
            y = Main.HEIGHT;
        } else if (y > Main.HEIGHT) {
            y = -height;
        }
    }

    public boolean hit(Entity e) {
        //         double x1 = x + width/2;
        //         double x2 = e.getX() + e.getWidth()/2;
        //         double y1 = y + height/2;
        //         double y2 = e.getY() + e.getHeight()/2;
        //         double disX = x1 - x2;
        //         double disY = y1 - y2;
        //         double dist = Math.sqrt(Math.pow(disX, 2)+ Math.pow(disY, 2));
        //         
        //         if(dist < height / 2 + e.getHeight() / 2)
        //         {
        //             return true;
        //         } else
        //         {
        //             return false;            
        //         }
        if (e != null) {
            return getHitbox().intersects(e.getHitbox());
        } else {
            return false;
        }
    }

    public Rectangle getHitbox() {
        return new Rectangle((int) x, (int) y, width, height);
    }
}
