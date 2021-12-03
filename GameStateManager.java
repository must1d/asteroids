import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameStateManager {
    private GameState currentGameState;

    private GameState menu;
    private GameState game;
    private GameState settings;
    private GameState pause;

    public GameStateManager() {
        menu = new MainMenu(this);
        game = new Game(this);
        settings = new Settings(this);
        pause = new Pause(this);

        currentGameState = menu;
    }

    public void render(Graphics g) {
        currentGameState.render(g);
    }

    public void update() {
        currentGameState.update();
    }

    public void switchState(GameState gameState) {
        currentGameState = gameState;
    }

    public GameState returnMenu() {
        return menu;
    }

    public GameState returnGame() {
        return game;
    }

    public GameState returnSettings() {
        return settings;
    }

    public GameState returnPause() {
        return pause;
    }

    public void newGame() {
        game = new Game(this);
    }

    public void keyPressed(KeyEvent e) {
        currentGameState.keyPressed(e);
    }

    public void keyReleased(KeyEvent e) {
        currentGameState.keyReleased(e);
    }

    public void keyTyped(KeyEvent e) {
        currentGameState.keyTyped(e);
    }

    public void mouseClicked(MouseEvent e) {
        currentGameState.mouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {
        currentGameState.mouseEntered(e);
    }

    public void mouseExited(MouseEvent e) {
        currentGameState.mouseExited(e);
    }

    public void mousePressed(MouseEvent e) {
        currentGameState.mousePressed(e);
    }

    public void mouseReleased(MouseEvent e) {
        currentGameState.mouseReleased(e);
    }
}

