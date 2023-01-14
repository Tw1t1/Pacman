import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class WelcomeState extends GameState {

	private JTextField playerNameField;
	private JLabel nameText;
	public static String playerName;
	private boolean isActive;

	public WelcomeState() {
		nameText = new JLabel("Write your name");
		nameText.setVisible(false);
		playerNameField = new JTextField();
		playerNameField.setVisible(false);
		playerName = "";
		isActive = true;
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
			if (currentText.length() > 0) {
				playerNameField.setText(currentText.substring(0, currentText.length() - 1));
			}
		} else if (aKeyCode == KeyEvent.VK_ENTER) {
			playerName = playerNameField.getText();
			PlayerData.setPlayerName(playerName);
			if (playerName.isEmpty()) {
				isActive = true;
			} else {
				isActive = false;
			}
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
		g.setColor(new Color(255, 215, 0));

		// Set font for title
		g.setFont(new Font("Pixel", Font.BOLD, 36));

		String text = "PACMAN GAME";
		int textWidth = g.getFontMetrics().stringWidth(text);

		// Draw title in the middle of the screen
		g.drawString(text, (aGameFrameBuffer.getWidth() - textWidth) / 2, aGameFrameBuffer.getHeight() / 2 - 50);

		// Reset font for rest of the elements
		g.setFont(new Font("Arial", Font.PLAIN, 12));

		// Draw the name text
		g.setColor(Color.white);
		g.drawRect((aGameFrameBuffer.getWidth() - textWidth) / 2 + 80, aGameFrameBuffer.getHeight() / 2 + 50, 100, 30);
		g.drawString(playerNameField.getText(), (aGameFrameBuffer.getWidth() - textWidth) / 2 + 82,
				aGameFrameBuffer.getHeight() / 2 + 70);

		// Draw text field
		g.setColor(Color.white);
		g.drawString(nameText.getText(), (aGameFrameBuffer.getWidth() - textWidth) / 2 + 85,
				aGameFrameBuffer.getHeight() / 2 + 40);
	}

}