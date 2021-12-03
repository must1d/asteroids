import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
    private Main main;

    public KeyManager(Main main_) {
        main = main_;
    }

    public void keyPressed(KeyEvent e) {
        main.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        main.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {
        main.keyTyped(e);
    }
}
