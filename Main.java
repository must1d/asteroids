import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Main implements Runnable {
    public static int WIDTH = 1200, HEIGHT = WIDTH / 16 * 9;

    private boolean running = false;

    private Thread thread;
    private Display display;

    private BufferStrategy bs;
    private Graphics g;

    private KeyManager keyManager;
    private MouseManager mouseManager;

    private GameStateManager gms;

    private BufferedImage background;

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public Main() {
    }

    public void run() {
        init();

        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / 60;
        final double timeF = 1000000000 / 60;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (running) {

            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                render();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                frames = 0;
                ticks = 0;
                timer += 1000;
            }
        }

        stop();
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        Assets.init();
        background = Assets.space;
        keyManager = new KeyManager(this);
        mouseManager = new MouseManager(this);
        gms = new GameStateManager();
        display = new Display("Asteroids", WIDTH, HEIGHT);
        display.getFrame().addKeyListener(keyManager);
        display.getCanvas().addMouseListener(mouseManager);
    }

    public void update() {
        gms.update();
    }

    public void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = bs.getDrawGraphics();

        //clears display
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background, 0, 0, null);
        //g.fillRect(WIDTH/2 - 200, HEIGHT / 2 - 70, 400, 150);

        //rendering
        gms.render(g);
        //End of rendering

        bs.show();
        g.dispose();
    }

    public void keyPressed(KeyEvent e) {
        gms.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        gms.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {
        gms.keyTyped(e);
    }

    public void mouseClicked(MouseEvent e) {
        gms.mouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {
        gms.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        gms.mouseExited(e);
    }

    public void mousePressed(MouseEvent e) {
        gms.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        gms.mouseReleased(e);
    }
}