import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Navbar {
    private int x;
    private int y;
    private int width;
    private int height;
    private int score;
    private int lives;

    public Navbar() {
        x = 0;
        y = 0;
        width = 640;
        height = 58;
        score = 0;
        lives = 3;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        // Draw the player name, score, and lives
        g.setColor(Color.WHITE);
        g.setFont(new Font("default", Font.BOLD, 20));
        g.drawString("Player: " + PlayerData.playerName + "                       Score: " + score + "                       Lives: " + lives, x + 10, y + 35);
    }
}
