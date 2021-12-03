import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public abstract class Menu extends GameState {
    protected int titleX, titleY;

    protected Button[] buttons;

    public Menu(GameStateManager gsm) {
        super(gsm);
    }

    public void render(Graphics g) {
    }

    public void update() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].update();
        }
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
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
        for (int i = 0; i < buttons.length; i++) {
            if (buttons[i].onButton(e.getX(), e.getY())) {
                buttons[i].pressed();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    protected void resetButtons() {
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].notPressed();
        }
    }
}
