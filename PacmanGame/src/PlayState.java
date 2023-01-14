import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Graphics;

public class PlayState extends GameState {
	private boolean active;
	private boolean gameOver;
	private int score;
	private float deltaTimeAverage;
	private String message;
	private Pacman pacman;
	private List<Ghost> ghosts;
	private List<Coin> coins;

	public PlayState() {
		score = 0;
		gameOver = false;
		pacman = new Pacman();
		ghosts = new ArrayList<>();
		coins = new ArrayList<>();
		ghosts.add(new Ghost(300, 200));
		ghosts.add(new Ghost(160, 200));
		ghosts.add(new Ghost(230, 100));
		ghosts.add(new Ghost(340, 320));
		coins.add(new Coin(300, 120));
		coins.add(new Coin(350, 120));
		coins.add(new Coin(400, 120));
	}

	public void enter(Object memento) {
		active = true;
		deltaTimeAverage = 0;
		if (gameOver) {
			pacman.reset();
			resetCoins();
			resetGhosts();
			score = 0;
			gameOver = false;
		}
	}

	public void processKeyReleased(int aKeyCode) {
		pacman.processKeyReleased(aKeyCode);
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;

	}

	public void update(long deltaTime) {
		if (gameOver) {
			active = false;
		} else {
			deltaTimeAverage = deltaTimeAverage * 0.9f + 0.1f * (float) deltaTime;
			pacman.pacmanMovement(deltaTime);
			ghostUpdate(deltaTime);
			ghostsCollision();
			coinsCollision();
		}
	}

	public void ghostUpdate(long deltaTime) {
		for (Ghost boo : ghosts) {
			boo.ghostMovement(deltaTime);
		}
	}

	public boolean isActive() {
		return active;
	}

	public String next() {
		return "Welcome";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		drawPacman(g);
		drawGhosts(g);
		drawCoins(g);
		drawScore(g);
	}

	private void ghostsCollision() {
		for (Ghost boo : ghosts) {
			if (boo.checkCollisionArray(pacman))
				gameOver = true;
		}
	}

	private void coinsCollision() {
		for (Coin c : coins) {
			if (c.isVisible() && c.checkCollision(pacman))
				score += c.coinScore();
		}
	}

	private void resetCoins() {
		for (Coin c : coins)
			c.setVisible(true);
	}

	private void resetGhosts() {
		for (Ghost boo : ghosts) {
			boo.reset();
		}

	}

	private void drawPacman(Graphics g) {
		g.drawImage(pacman.getImage(), (int) pacman.getX(), (int) pacman.getY(), null);
	}

	private void drawGhosts(Graphics g) {

		for (Ghost boo : ghosts) {
			g.drawImage(boo.getImage(), (int) boo.getX(), (int) boo.getY(), null);
		}

	}

	private void drawCoins(Graphics g) {
		g.setColor(Color.green);
		for (Coin c : coins) {
			if (c.isVisible())
				g.fillOval((int) c.getX(), (int) c.getY(), c.getWidth(), c.getHeight());
		}
	}

	private void drawScore(Graphics g) {
		message = "Score: " + score;
		g.setColor(Color.white);
		g.setFont(new Font("Ariel", Font.BOLD, 20));
		g.drawString(message, 10, 20);
	}
}
