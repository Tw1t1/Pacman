import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class GameWonState extends GameState{
    
    private PlayerData player;
    private boolean isActive;
    private SparklesAnimator sparklesAnimator;

    public GameWonState() {
        isActive = true;
        sparklesAnimator = new SparklesAnimator();
    }

    @Override
    public void enter(Object memento) {
        player = (PlayerData) memento;
        isActive = true;
    }

    @Override
    public void exit() {
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String next() {
        return "Welcome";
    }

    @Override
    public void processKeyReleased(int aKeyCode) {
        if (aKeyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (aKeyCode == KeyEvent.VK_ENTER) {
            isActive = false;
        }
    }
    @Override
    public void render(GameFrameBuffer aGameFrameBuffer) {
        Graphics2D g = aGameFrameBuffer.graphics();
        
        sparklesAnimator.update();
        sparklesAnimator.render(g);
        
        g.setColor(new Color(255, 215, 0));

        // Set font for title
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/PAC-FONT.TTF"))
                    .deriveFont(Font.PLAIN, 60);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            g.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        String text = "YOU WON!";
        int textWidth = g.getFontMetrics().stringWidth(text);

        // Draw title in the middle of the screen
        g.drawString(text, (aGameFrameBuffer.getWidth() - textWidth) / 2, aGameFrameBuffer.getHeight() / 2 - 50);

        // Reset font for rest of the elements
        g.setColor(Color.white);
        try {
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/Pixeboy-z8XGD.ttf"))
                    .deriveFont(Font.PLAIN, 28);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
            g.setFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        g.drawString(player.getPlayerName() + " has a score of: " + player.getPlayerScore(), (aGameFrameBuffer.getWidth() - textWidth) / 2 + 100,
                aGameFrameBuffer.getHeight() / 2 + 40);

        g.drawString("Press enter to play again and esc to exit the game", (aGameFrameBuffer.getWidth() - textWidth) / 2 - 60,
                aGameFrameBuffer.getHeight() / 2 + 80);
    }
}
