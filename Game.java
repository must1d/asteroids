import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class Game extends GameState {
    private Player player;
    private ArrayList<Asteroid> asteroids;
    private ArrayList<Shot> shots;
    private ArrayList<PowerUp> powerups;

    private PowerUp currentPowerUp;

    private boolean gameOver;
    private int wave;

    private boolean alreadyShot;

    private int startingAsteroids;
    private int numberOfAsteroidsAdded;

    private int score = 0;

    private BufferedImage GameOverScreen;
    private String nameDesSpielers = "";

    public Game(GameStateManager gsm) {
        super(gsm);
        player = new Player();
        asteroids = new ArrayList<Asteroid>();
        shots = new ArrayList<Shot>();
        powerups = new ArrayList<PowerUp>();

        currentPowerUp = null;

        startingAsteroids = 8;
        numberOfAsteroidsAdded = 2;
        wave = 1;
        wave(startingAsteroids);

        score = 0;

        gameOver = false;
    }

    public void render(Graphics g) {
        for (int i = powerups.size() - 1; i >= 0; i--) {
            PowerUp p = powerups.get(i);
            p.render(g);
        }

        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            if (shot != null) {
                shot.render(g);
            }
        }

        for (int i = asteroids.size() - 1; i >= 0; i--) {
            if (i < asteroids.size()) {
                Asteroid asteroid = asteroids.get(i);
                asteroid.render(g);
            }
        }

        player.render(g);

        if (gameOver) {
            Font font = new Font("Zorque", Font.PLAIN, 100);
            FontMetrics metrics = g.getFontMetrics(font);
            g.setFont(font);
            g.setColor(new Color(255, 255, 255));

            int x = (Main.WIDTH - metrics.stringWidth("Game Over")) / 2;
            int y = 140;

            g.drawString("Game Over", x, y);

            font = new Font("Zorque", Font.PLAIN, 50);
            metrics = g.getFontMetrics(font);
            g.setFont(font);
            g.setColor(new Color(255, 255, 255));

            String yourscore = "Your Score: " + score;

            x = (Main.WIDTH - metrics.stringWidth(yourscore)) / 2;
            y += 150;

            g.drawString(yourscore, x, y);

            String enter = "Enter your Name and press 'Enter'";
            x = (Main.WIDTH - metrics.stringWidth(enter)) / 2;
            y += 100;

            g.drawString(enter, x, y);

            x = (Main.WIDTH - metrics.stringWidth(nameDesSpielers)) / 2;
            y += 100;

            g.drawString(nameDesSpielers, x, y);
        } else {
            //render information
            Font font = new Font("Zorque", Font.PLAIN, 50);
            FontMetrics metrics = g.getFontMetrics(font);
            g.setFont(font);
            g.setColor(new Color(255, 255, 255));

            int y = 50;
            int x = 20;

            g.drawString("Score: " + score, x, y);

            y += 30;

            BufferedImage image = Assets.player;
            for (int i = 0; i < player.returnLeben(); i++) {
                if (x + image.getWidth() + 20 > Main.WIDTH) {
                    x = 20;
                    y += 20 + image.getHeight();
                }
                g.drawImage(image, x, y, null);
                x += image.getWidth() + 20;
            }
            y += 20 + image.getHeight();
            x = 20;
            if (currentPowerUp != null) {
                switch (currentPowerUp.getType()) {
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
                g.drawImage(image, x, y, null);
            }
        }
    }

    public void update() {
        if (!gameOver) {
            player.update();
            CollisionDetection();
        }
        for (int i = shots.size() - 1; i >= 0; i--) {
            Shot shot = shots.get(i);
            shot.update();
            if (shot.isDead()) {
                shots.remove(i);
            }
        }
        for (Asteroid asteroid : asteroids) {
            asteroid.update();
        }
        if (asteroids.size() == 0) {
            wave(startingAsteroids + wave * numberOfAsteroidsAdded);
            wave++;
        }
    }

    private void asteroidSpawnen() {
        int x, y;
        double ort = Math.random();

        int height = 0, width = 0;

        if (Assets.asteroidGross != null) {
            width = Assets.asteroidGross.getWidth();
            height = Assets.asteroidGross.getHeight();
        }

        double perimeter = Main.WIDTH * 2 + Main.HEIGHT * 2;

        double chanceTop = Main.WIDTH / perimeter;
        double chanceBottom = (Main.WIDTH / perimeter) + chanceTop;
        double chanceLeft = (Main.HEIGHT / perimeter) + chanceBottom;
        double chanceRight = (Main.HEIGHT / perimeter) + chanceLeft;

        if (ort >= 0 && ort < chanceTop) {
            y = -height;
            x = (int) (Math.random() * Main.WIDTH);
        } else if (ort >= chanceTop && ort < chanceBottom) {
            y = Main.HEIGHT;
            x = (int) (Math.random() * Main.WIDTH);
        } else if (ort >= chanceBottom && ort < chanceLeft) {
            y = (int) (Math.random() * Main.HEIGHT);
            x = -width;
        } else {
            y = (int) (Math.random() * Main.HEIGHT);
            x = Main.WIDTH;
        }

        double velX, velY;

        int min = 1;
        int max = 2;

        double vel = Math.random() * (max - min) + min;

        int xTarget, yTarget;

        xTarget = Main.WIDTH / 2 + (int) (Math.random() * (200 + 200) - 200);
        yTarget = Main.HEIGHT / 2 + (int) (Math.random() * (75 + 75) - 75);

        double deltaX, deltaY;

        deltaX = x - xTarget;
        deltaY = y - yTarget;

        if (deltaX != 0) {
            double angle = Math.atan(deltaY / deltaX);
            if (deltaX > 0) {
                velX = -vel * Math.cos(angle);
                velY = -vel * Math.sin(angle);
            } else {
                velX = vel * Math.cos(angle);
                velY = vel * Math.sin(angle);
            }
            asteroids.add(new Asteroid(x, y, velX, velY, 3));
        }
    }

    private void newAsteroid(int index) {
        Asteroid asteroid = asteroids.get(index);
        if (Math.random() < 0.1) {
            randomPowerUp(asteroid.getX(), asteroid.getY());
        }
        int size = asteroid.getSize() - 1;
        if (size + 1 == 3) {
            score += 20;
        } else if (size + 1 == 2) {
            score += 50;
        } else {
            score += 100;
        }
        double previousVel = asteroid.getVel();
        if (size > 0) {
            double min = 0.2;
            double max = 0.8;
            double vel;
            int angle;
            for (int i = 0; i < 2; i++) {
                vel = previousVel + Math.random() * (max - min) + min;

                angle = (int) (Math.random() * 360);
                double newVelX = vel * Math.sin(Math.toRadians(angle));
                double newVelY = vel * Math.cos(Math.toRadians(angle));

                asteroid = new Asteroid(asteroid.getX(), asteroid.getY(), newVelX, newVelY, size);
                asteroids.add(asteroid);
            }
        }
        asteroids.remove(index);
    }

    private void randomPowerUp(double x, double y) {
        double random = Math.random();

        if (random >= 0 && random < 0.3) {
            powerups.add(new PowerUp(x, y, PowerUpTypes.SPEED));
        } else if (random >= 0.3 && random < 0.6) {
            powerups.add(new PowerUp(x, y, PowerUpTypes.INVINCIBILITY));
        } else if (random >= 0.6 && random < 0.9) {
            powerups.add(new PowerUp(x, y, PowerUpTypes.LEBEN));
        } else {
            powerups.add(new PowerUp(x, y, PowerUpTypes.DESTROYALLASTEROIDS));
        }
    }

    private void shot() {
        double xVel;
        double yVel;
        double x;
        double y;

        int width = 4;
        int height = 4;
        int vel = 10;

        x = player.xSpitze() - width / 2;
        y = player.ySpitze() - height / 2;
        xVel = vel * Math.sin(Math.toRadians(player.getAngle()));
        yVel = -vel * Math.cos(Math.toRadians(player.getAngle()));

        shots.add(new Shot(x, y, xVel, yVel, width, height));
    }

    private void wave(int number) {
        for (int i = 0; i < number; i++) {
            asteroidSpawnen();
        }
    }

    private void CollisionDetection() {
        if (!player.isDead()) {
            for (int i = powerups.size() - 1; i >= 0; i--) {
                PowerUp p = powerups.get(i);
                if (player.hit(p)) {
                    if (currentPowerUp == null) {
                        currentPowerUp = p;
                        powerups.remove(i);
                    }
                }
            }
        }
        for (int i = asteroids.size() - 1; i >= 0; i--) {
            Asteroid asteroid = asteroids.get(i);
            for (int y = shots.size() - 1; y >= 0; y--) {
                Shot shot = shots.get(y);
                if (asteroid.hit(shot)) {
                    newAsteroid(i);
                    shots.remove(y);
                    return;
                }
            }
            if (asteroid.hit(player) && !player.invincible() && !player.isRespawning()) {
                newAsteroid(i);
                player.death();
                if (!player.heartsLeft()) {
                    gameOver = true;
                }
                return;
            }
        }
    }

    public void keyPressed(KeyEvent e) {
        if (gameOver) {
            if (e.getKeyCode() == 10 && nameDesSpielers.length() > 0) {
                gsm.switchState(gsm.returnMenu());
            }
            if (e.getKeyCode() == 8) {
                if (nameDesSpielers.length() > 0) {
                    nameDesSpielers = nameDesSpielers.substring(0, nameDesSpielers.length() - 1);
                }
            }
            if (nameDesSpielers.length() < 29) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) {
                    nameDesSpielers += c;
                } else if (c == ' ') {
                    nameDesSpielers += " ";
                }
            }
        } else if (e.getKeyCode() == 87) {
            player.setForward(true);
        } else if (e.getKeyCode() == 68) {
            player.setRotateRight(true);
        } else if (e.getKeyCode() == 65) {
            player.setRotateLeft(true);
        } else if (e.getKeyCode() == 32) {
            if (!player.isDead()) {
                if (!alreadyShot) {
                    alreadyShot = true;
                    shot();
                }
            }
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            gsm.switchState(gsm.returnPause());
        } else if (e.getKeyCode() == 16) {
            if (currentPowerUp != null && !player.isDead()) {
                switch (currentPowerUp.getType()) {
                    case SPEED:
                        player.force();
                        break;
                    case INVINCIBILITY:
                        player.setInvincible();
                        break;
                    case LEBEN:
                        player.addLeben();
                        break;
                    case DESTROYALLASTEROIDS:
                        for (int j = asteroids.size() - 1; j >= 0; j--) {
                            newAsteroid(j);
                        }
                        break;
                }
                currentPowerUp = null;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 87) {
            player.setForward(false);
        }
        if (e.getKeyCode() == 68) {
            player.setRotateRight(false);
        }
        if (e.getKeyCode() == 65) {
            player.setRotateLeft(false);
        }
        if (e.getKeyCode() == 32) {
            alreadyShot = false;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }
}