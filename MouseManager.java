import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener {
    private Main main;

    public MouseManager(Main main_) {
        main = main_;
    }

    public void mouseClicked(MouseEvent e) {
        main.mouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {
        main.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        main.mouseExited(e);
    }

    public void mousePressed(MouseEvent e) {
        main.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        main.mouseReleased(e);
    }
}