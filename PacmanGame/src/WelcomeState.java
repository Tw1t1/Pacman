import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

public class WelcomeState extends GameState {

	private String playerName;
	private final int playerNameMaxLen = 15;
	private PlayerData player;
	private int difficulty;
	private boolean isActive;
	private SparklesAnimator sparklesAnimator;

	public WelcomeState() {
		sparklesAnimator = new SparklesAnimator();
	}

	@Override
	public void enter(Object memento) {
		playerName = "";
		player = new PlayerData();
		difficulty = 0;
		isActive = true;
	}

	@Override
	public void exit() {
		Ghost.setGhostSpeed(difficulty);
	}

	@Override
	public boolean isActive() {
		return isActive;
	}

	@Override
	public String next() {
		return "Play";
	}

	@Override
	public void processKeyPressed(int aKeyCode) {
		if (aKeyCode >= KeyEvent.VK_A && aKeyCode <= KeyEvent.VK_Z) {
			if(playerName.length() < playerNameMaxLen)
				playerName += (char)aKeyCode;
		} else if (aKeyCode == KeyEvent.VK_BACK_SPACE) {
			if (playerName.length() > 0)
				playerName = playerName.substring(0, playerName.length() - 1);
		} else if (aKeyCode == KeyEvent.VK_ENTER) {
			player.setPlayerName(playerName);
			if (!player.getPlayerName().isEmpty())
				isActive = false;
		}
		if(aKeyCode == KeyEvent.VK_1) {
			difficulty = 0;
        }
        if(aKeyCode == KeyEvent.VK_2) {
			difficulty = 1;
        }
        if(aKeyCode == KeyEvent.VK_3) {
			difficulty = 2;
        }
	}

	@Override
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE) {
			System.exit(0);
		}
	}

	@Override
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics2D g = aGameFrameBuffer.graphics();
		String text;
		int width = aGameFrameBuffer.getWidth();
		int height = aGameFrameBuffer.getHeight();
		int fontHeight;

		sparklesAnimator.update();
		sparklesAnimator.render(g);

		// Set font for title
		g.setColor(new Color(255, 215, 0));
		try {
			Font pacmanFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/PAC-FONT.TTF")).deriveFont(Font.PLAIN, 60);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pacmanFont);
			g.setFont(pacmanFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		fontHeight = g.getFontMetrics().getHeight();

		// Draw title in the middle of the screen
		text = "PACMAN GAME";
		int titleWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (width - titleWidth) / 2f, (height - fontHeight) / 2.5f);

		// Reset font for rest of the elements
		g.setColor(Color.white);
		try {
			Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/Pixeboy-z8XGD.ttf")).deriveFont(Font.PLAIN, 28);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pixelFont);
			g.setFont(pixelFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		fontHeight = g.getFontMetrics().getHeight();

		// Draw name line
		text = "Write your name";
		int nameLineWidth = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (width - nameLineWidth) / 2,
				height / 2 - fontHeight);

		// Draw the name box & string
		int nameBoxWidth = g.getFontMetrics().stringWidth("A") * (playerNameMaxLen + 1);
		int nameBoxHeight = fontHeight;
		g.drawRect((width - nameBoxWidth) / 2, (height - nameBoxHeight) / 2, nameBoxWidth, nameBoxHeight);

		int nameWidth = g.getFontMetrics().stringWidth(playerName);
		g.drawString(playerName, (width - nameBoxWidth) / 2f + (nameBoxWidth - nameWidth) / 2f,
				(height - nameBoxHeight + (1.5f * fontHeight)) / 2f);

		// Draw the buttons
		int strWidth;
		int buttonWidth = 120;
		int buttonHeight = 50;
		int btnY = 3 * height / 4;
		int btnHardX = (width - buttonWidth) / 2;
		int btnEasyX = btnHardX - (int)(1.5f * buttonWidth);
		int btnExpertX = btnHardX + (int)(1.5f * buttonWidth);
		int lblX = (buttonWidth - g.getFontMetrics().stringWidth("Press 0")) / 2;
		int lblY = btnY - fontHeight / 2;
		int strY = btnY + buttonHeight / 2 + fontHeight / 3;

		g.drawRect(btnEasyX, btnY, buttonWidth, buttonHeight);
		g.drawRect(btnHardX, btnY, buttonWidth, buttonHeight);
		g.drawRect(btnExpertX, btnY, buttonWidth, buttonHeight);

		g.drawString("Press 1", btnEasyX + lblX, lblY);
		g.drawString("Press 2", btnHardX + lblX, lblY);
		g.drawString("Press 3", btnExpertX + lblX, lblY);

		// Set font for the buttons text
		g.setColor(new Color(255, 215, 0));
		try {
			Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/Pixeboy-z8XGD.ttf")).deriveFont(Font.PLAIN, 36);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pixelFont);
			g.setFont(pixelFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		text = "Easy";
		strWidth = g.getFontMetrics().stringWidth(text);
		g.drawString("Easy", btnEasyX + (buttonWidth - strWidth) / 2, strY);

		text = "Hard";
		strWidth = g.getFontMetrics().stringWidth(text);
		g.drawString("Hard", btnHardX + (buttonWidth - strWidth) / 2, strY);

		text = "Expert";
		strWidth = g.getFontMetrics().stringWidth(text);
		g.drawString("Expert", btnExpertX + (buttonWidth - strWidth) / 2, strY);
	}

	@Override
	public Object memento() {
		return player;
	}
}