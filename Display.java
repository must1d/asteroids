import javax.swing.JFrame;

import java.awt.Canvas;
import java.awt.Dimension;

public class Display {
    private JFrame frame;
    private Canvas canvas;

    private String title;
    private int width, height;

    public Display(String title_, int width_, int height_) {
        title = title_;
        width = width_;
        height = height_;

        createDisplay();
    }

    private void createDisplay() {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public JFrame getFrame() {
        return frame;
    }
}
