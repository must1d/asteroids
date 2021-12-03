import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class Pause extends Menu {
    public Pause(GameStateManager gsm) {
        super(gsm);

        buttons = new Button[2];

        int buttonY = titleY + 140;
        buttons[0] = new Button(buttonY, Assets.ResumeGame, Assets.ResumeGamePressed);//Resume Game

        buttonY += buttons[0].getHeight() + 30;
        buttons[1] = new Button(buttonY, Assets.ReturnToMainMenu, Assets.ReturnToMainMenuPressed);
        //Return to Menu

    }

    public void mouseReleased(MouseEvent e) {
        if (buttons[0].onButton(e.getX(), e.getY()) && buttons[0].isPressed()) {
            gsm.switchState(gsm.returnGame());
        } else if (buttons[1].onButton(e.getX(), e.getY()) && buttons[1].isPressed()) {
            gsm.switchState(gsm.returnMenu());
        }
        resetButtons();
    }

    public void render(Graphics g) {
        Font font = new Font("Zorque", Font.PLAIN, 100);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        g.setColor(new Color(255, 255, 255));

        titleX = (Main.WIDTH - metrics.stringWidth("Pause")) / 2;
        titleY = 110;

        g.drawString("Pause", titleX, titleY);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].render(g);
        }
    }
}
