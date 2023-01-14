import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.io.File;
import java.awt.GraphicsEnvironment;
import java.awt.FontFormatException;
import java.io.IOException;

public class Navbar {
    private int x;
    private int y;
    private int width;
    private int height;

    public Navbar() {
        x = 0;
        y = 0;
        width = 1170;
        height = 100;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(x + 10, y + 20, width, height);
        g.setColor(Color.BLUE);
        g.drawRect(x + 10, y + 20, width, height);
    
        // Draw the player name, score, and lives
        g.setColor(Color.WHITE);
        try {
            Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/src/utils/Pixeboy-z8XGD.ttf")).deriveFont(Font.PLAIN, 60);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(pixelFont);
            g.setFont(pixelFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        g.drawString("Player: " + PlayerData.playerName + "                  Score: " + PlayerData.playerScore + "                 Lives: " + PlayerData.playerLives, x + 70, y + 85);
    }    
}
