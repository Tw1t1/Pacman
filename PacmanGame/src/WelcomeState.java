import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class WelcomeState extends GameState {

	private JTextField playerNameField;
	private JLabel nameText;
	private PlayerData player;
	private boolean isActive;
	private SparklesAnimator sparklesAnimator;

	public WelcomeState() {
		nameText = new JLabel("Write your name");
		nameText.setVisible(false);
		playerNameField = new JTextField();
		playerNameField.setVisible(false);
		player = new PlayerData();
		sparklesAnimator = new SparklesAnimator();
	}

	@Override
	public void enter(Object memento) {
		playerNameField.setVisible(true);
		nameText.setVisible(true);
		isActive = true;
	}

	@Override
	public void exit() {
		playerNameField.setVisible(false);
		nameText.setVisible(false);
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
		if ((aKeyCode >= KeyEvent.VK_A && aKeyCode <= KeyEvent.VK_Z)
				|| (aKeyCode >= KeyEvent.VK_0 && aKeyCode <= KeyEvent.VK_9)) {
			playerNameField.setText(playerNameField.getText() + (char) aKeyCode);
		} else if (aKeyCode == KeyEvent.VK_BACK_SPACE) {
			String currentText = playerNameField.getText();
			if (currentText.length() > 0)
				playerNameField.setText(currentText.substring(0, currentText.length() - 1));
		} else if (aKeyCode == KeyEvent.VK_ENTER) {
			player.setPlayerName(playerNameField.getText());
			if (!player.getPlayerName().isEmpty())
				isActive = false;
		}
		if(aKeyCode == KeyEvent.VK_1) {
            Ghost.setGhostSpeed(0);
        }
        if(aKeyCode == KeyEvent.VK_2) {
            Ghost.setGhostSpeed(1);
        }
        if(aKeyCode == KeyEvent.VK_3) {
            Ghost.setGhostSpeed(2);
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
		Graphics2D b = aGameFrameBuffer.graphics();

		sparklesAnimator.update();
		sparklesAnimator.render(g);

		g.setColor(new Color(255, 215, 0));

		// Set font for title
		try {
			Font pacmanFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/PAC-FONT.TTF")).deriveFont(Font.PLAIN, 60);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pacmanFont);
			g.setFont(pacmanFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		String text = "PACMAN GAME";
		int textWidth = g.getFontMetrics().stringWidth(text);

		// Draw title in the middle of the screen
		g.drawString(text, (aGameFrameBuffer.getWidth() - textWidth) / 2, aGameFrameBuffer.getHeight() / 2 - 50);

		// Reset font for rest of the elements
		try {
			Font pixelFont = Font.createFont(Font.TRUETYPE_FONT, new File("PacmanGame/utils/Pixeboy-z8XGD.ttf")).deriveFont(Font.PLAIN, 28);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(pixelFont);
			g.setFont(pixelFont);
			b.setFont(pixelFont);
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}

		// Draw the name text
		g.setColor(Color.white);
		g.drawRect((aGameFrameBuffer.getWidth() - textWidth) / 2 + 210, aGameFrameBuffer.getHeight() / 2 + 50, 230, 30);
		g.drawString(playerNameField.getText(), (aGameFrameBuffer.getWidth() - textWidth) / 2 + 220,
				aGameFrameBuffer.getHeight() / 2 + 70);

		// Draw text field
		g.setColor(Color.white);
		g.drawString(nameText.getText(), (aGameFrameBuffer.getWidth() - textWidth) / 2 + 230,
				aGameFrameBuffer.getHeight() / 2 + 40);
		
		b.setColor(new Color(255, 215, 0));
        // Draw the easy button
    	g.drawRect(aGameFrameBuffer.getWidth()/3 + 85 - 50, aGameFrameBuffer.getHeight() - 75, 100, 50);
   	 	b.drawString("Easy", aGameFrameBuffer.getWidth()/3 + 100 - 40, aGameFrameBuffer.getHeight() - 40);
		g.drawString("Press 1", aGameFrameBuffer.getWidth()/3 + 85 - 40, aGameFrameBuffer.getHeight() - 40 - 50);

    	// Draw the hard button
    	g.drawRect((aGameFrameBuffer.getWidth()/3) + 205 - 50, aGameFrameBuffer.getHeight() - 75, 100, 50);
    	b.drawString("Hard", (aGameFrameBuffer.getWidth()/3) + 215 - 40, aGameFrameBuffer.getHeight() - 40);
		g.drawString("Press 2", aGameFrameBuffer.getWidth()/3 + 205 - 40, aGameFrameBuffer.getHeight() - 40 - 50);

    	// Draw the expert button
    	g.drawRect((aGameFrameBuffer.getWidth()/3) + 325 - 50, aGameFrameBuffer.getHeight() - 75, 100, 50);
    	b.drawString("Expert", (aGameFrameBuffer.getWidth()/3) + 325 - 40, aGameFrameBuffer.getHeight() - 40);
		g.drawString("Press 3", aGameFrameBuffer.getWidth()/3 + 325 - 40, aGameFrameBuffer.getHeight() - 40 - 50);
	}

	@Override
	public Object memento() {
		return player;
	}
}