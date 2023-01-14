import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayState extends GameState {

	private boolean active;
	private boolean gameOver;
	private int score;
	private float deltaTimeAverage;
	private String message;
	private Pacman pacman;
	private ArrayList<GameObject> ghosts;
	private ArrayList<Coin> coins;
	
	public PlayState() {
		score = 0;
		gameOver = false;
		pacman = new Pacman();
		ghosts = new ArrayList<>();
		coins = new ArrayList<>();
		ghosts.add(new GameObject(550, 100, 50, 50));
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
			score = 0;
			gameOver = false;
		}
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if (aKeyCode == KeyEvent.VK_Q)
			active = false;
			
	}
	
	public void update(long deltaTime) {
		if (gameOver) {
			if(!pacman.shrink())
				active = false;
		}
		else {
			deltaTimeAverage = deltaTimeAverage* 0.9f + 0.1f*(float)deltaTime;
			float x = pacman.getX() + 0.1f*deltaTime;
			if (x > Game.WIDTH)
				x = x - Game.WIDTH;
			pacman.setX(x);
			ghostsCollision();
			coinsCollision();
		}
	}
	
	public boolean isActive() { return active; }
	
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
		if (pacman.checkCollision(ghosts))
			gameOver = true;
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

	private void drawPacman(Graphics g) {
		g.setColor(Color.yellow);
		g.fillOval((int)pacman.getX(), (int)pacman.getY(), pacman.getWidth(), pacman.getHeight());
	}

	private void drawGhosts(Graphics g) {
		g.setColor(Color.red);
		for (GameObject o : ghosts)
			g.fillOval((int)o.getX(), (int)o.getY(), o.getWidth(), o.getHeight());
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
