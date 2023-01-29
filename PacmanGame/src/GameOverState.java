import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;

public class GameOverState extends GameState {

	private PlayerData player;
	private boolean isActive;
	private SparklesAnimator sparklesAnimator;

	public GameOverState() {
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
		Rectangle2D bounds;
		String text;
		int width = aGameFrameBuffer.getWidth();
		int height = aGameFrameBuffer.getHeight();

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

		// title
		text = player.isWon() ? "YOU WON!" : "GAME OVER";
		bounds = g.getFontMetrics().getStringBounds(text, g);
		int titleWidth = (int)bounds.getWidth();
		int titleHeight = (int)bounds.getHeight();

		// Draw title in the middle of the screen
		g.drawString(text, (width - titleWidth) / 2, (height - titleHeight) / 2);

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
		// line 1
		text = player.getPlayerName() + " has a score of: " + player.getPlayerScore();
		bounds = g.getFontMetrics().getStringBounds(text, g);
		int line1Width = (int)bounds.getWidth();
		int line1Height = (int)bounds.getHeight();

		// Draw line 1 under the title
		g.drawString(text, (width - line1Width) / 2, height / 2 + titleHeight);

		// line 2
		text = "Press enter to play again and esc to exit the game";
		bounds = g.getFontMetrics().getStringBounds(text, g);
		int line2Width = (int)bounds.getWidth();

		// Draw line 2 under line 1
		g.drawString(text, (width - line2Width) / 2, height / 2 + titleHeight + line1Height);
	}
}