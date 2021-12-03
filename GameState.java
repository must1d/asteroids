import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class GameState {
    protected GameStateManager gsm;

    public GameState(GameStateManager gsm_) {
        gsm = gsm_;
    }

    public abstract void render(Graphics g);

    public abstract void update();

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract void keyTyped(KeyEvent e);

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mouseEntered(MouseEvent e);

    public abstract void mouseExited(MouseEvent e);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);
}
