import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

public class InfoBar {
    public static final int HEIGHT = 50;
    private PlayerData player;

    public void setPlayer(PlayerData player){
        this.player = player;
    }

    public void render(Graphics2D g){
        Rectangle2D bounds;
        String playerName = "Player: " + player.getPlayerName();
        String playerScore = "Score: " + player.getPlayerScore();
        String playerLives = "Lives: " + player.getPlayerLives();

        g.setColor(Color.BLACK);
        g.fillRect(0, 1, Game.WIDTH-1, HEIGHT);
        g.setColor(Map.getLineColor());
        g.drawRect(0, 1, Game.WIDTH-1, HEIGHT);

        // Draw the player name, score, and lives
        g.setColor(Color.WHITE);
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/Pixeboy-z8XGD.ttf")).deriveFont(Font.PLAIN, 60);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
            g.setFont(pixelFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        int playerNameWidth = g.getFontMetrics().stringWidth(playerName);
        int playerScoreWidth = g.getFontMetrics().stringWidth(playerScore);
        int playerLivesWidth = g.getFontMetrics().stringWidth(playerLives);
        int stringY = (HEIGHT * 4 / 5) ;
        int space = (Game.WIDTH - (playerNameWidth + playerScoreWidth + playerLivesWidth)) / 4;

        g.drawString(playerName, space, stringY);
        g.drawString(playerScore, (2*space)+playerNameWidth, stringY);
        g.drawString(playerLives, (3*space)+playerNameWidth + playerScoreWidth, stringY);
    }
}