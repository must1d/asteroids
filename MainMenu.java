import java.awt.Graphics;
import java.awt.event.MouseEvent;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

public class MainMenu extends Menu {
    public MainMenu(GameStateManager gsm) {
        super(gsm);

        buttons = new Button[4];

        int buttonY = titleY + 140;
        buttons[0] = new Button(buttonY, Assets.StartGame, Assets.StartGamePressed);//Start Game

        buttonY += buttons[0].getHeight() + 30;
        buttons[1] = new Button(buttonY, Assets.HighScore, Assets.HighScorePressed);//Highscore

        buttonY += buttons[1].getHeight() + 30;
        buttons[2] = new Button(buttonY, Assets.Settings, Assets.SettingsPressed); //Options

        buttonY += buttons[2].getHeight() + 30;
        buttons[3] = new Button(buttonY, Assets.Quit, Assets.QuitPressed); //Quit
    }

    public void mouseReleased(MouseEvent e) {
        if (buttons[0].onButton(e.getX(), e.getY()) && buttons[0].isPressed()) {
            gsm.newGame();
            gsm.switchState(gsm.returnGame());
        } else if (buttons[1].onButton(e.getX(), e.getY()) && buttons[1].isPressed()) {
            //Highscore
        } else if (buttons[2].onButton(e.getX(), e.getY()) && buttons[2].isPressed()) {
            gsm.switchState(gsm.returnSettings());
        } else if (buttons[3].onButton(e.getX(), e.getY()) && buttons[3].isPressed()) {
            System.exit(0);
        }
        resetButtons();
    }

    public void render(Graphics g) {
        Font font = new Font("Zorque", Font.PLAIN, 100);
        FontMetrics metrics = g.getFontMetrics(font);
        g.setFont(font);
        g.setColor(new Color(255, 255, 255));

        titleX = (Main.WIDTH - metrics.stringWidth("Menu")) / 2;
        titleY = 110;

        g.drawString("Menu", titleX, titleY);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].render(g);
        }
    }
}
