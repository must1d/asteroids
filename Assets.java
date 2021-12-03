import java.awt.image.BufferedImage;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.FontFormatException;

public class Assets {
    public static BufferedImage asteroidGross;
    public static BufferedImage asteroidMittel;
    public static BufferedImage asteroidKlein;
    public static BufferedImage player;
    public static BufferedImage playerForward;
    public static BufferedImage PowerUpSpeed;
    public static BufferedImage PowerUpInvincibility;
    public static BufferedImage PowerUpLeben;
    public static BufferedImage PowerUpDestroyAllAsteroids;

    public static BufferedImage space;

    public static BufferedImage StartGame;
    public static BufferedImage StartGamePressed;

    public static BufferedImage ResumeGame;
    public static BufferedImage ResumeGamePressed;

    public static BufferedImage Settings;
    public static BufferedImage SettingsPressed;

    public static BufferedImage Quit;
    public static BufferedImage QuitPressed;

    public static BufferedImage ReturnToMainMenu;
    public static BufferedImage ReturnToMainMenuPressed;

    public static BufferedImage HighScore;
    public static BufferedImage HighScorePressed;

    public static void init() {
        Imageloader loader;
        loader = new Imageloader();

        asteroidGross = loader.loadImage("/res/textures/entities/asteroids/asteroidsGross.png");
        asteroidMittel = loader.loadImage("/res/textures/entities/asteroids/asteroidsMittel.png");
        asteroidKlein = loader.loadImage("/res/textures/entities/asteroids/asteroidsKlein.png");
        player = loader.loadImage("/res/textures/entities/player/player.png");
        playerForward = loader.loadImage("/res/textures/entities/player/playerForward.png");
        PowerUpSpeed = loader.loadImage("/res/textures/entities/powerups/Speed.png");
        PowerUpInvincibility = loader.loadImage("/res/textures/entities/powerups/Invincibility.png");
        PowerUpLeben = loader.loadImage("/res/textures/entities/powerups/Leben.png");
        PowerUpDestroyAllAsteroids = loader.loadImage("/res/textures/entities/powerups/DestroyAllAsteroids.png");

        space = loader.loadImage("/res/textures/backgrounds/Space.jpg");

        StartGame = loader.loadImage("/res/textures/buttons/StartGame.png");
        StartGamePressed = loader.loadImage("/res/textures/buttons/StartGamePressed.png");

        ResumeGame = loader.loadImage("/res/textures/buttons/ResumeGame.png");
        ResumeGamePressed = loader.loadImage("/res/textures/buttons/ResumeGamePressed.png");

        Settings = loader.loadImage("/res/textures/buttons/Settings.png");
        SettingsPressed = loader.loadImage("/res/textures/buttons/SettingsPressed.png");

        Quit = loader.loadImage("/res/textures/buttons/Quit.png");
        QuitPressed = loader.loadImage("/res/textures/buttons/QuitPressed.png");

        ReturnToMainMenu = loader.loadImage("/res/textures/buttons/ReturnToMainMenu.png");
        ReturnToMainMenuPressed = loader.loadImage("/res/textures/buttons/ReturnToMainMenuPressed.png");

        HighScore = loader.loadImage("/res/textures/buttons/HighScore.png");
        HighScorePressed = loader.loadImage("/res/textures/buttons/HighScorePressed.png");

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, Assets.class.getResourceAsStream("/res/font/zorque.ttf")));
        } catch (IOException | FontFormatException e) {
            System.out.println("fail");
        }
    }
}
