import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
	private final int ghostsNumber = 5;
	private boolean active;
	private boolean pacmanDied;
	private boolean gameOver;
	private boolean gameWon;
	private float deltaTimeAverage;
	private PlayerData player;
	private InfoBar infoBar;
	private Map map;
	private Pacman pacman;
	private List<Ghost> ghosts;
	private List<Coin> coins;

	public PlayState() {
		infoBar = new InfoBar();
		map = new Map();
		pacmanDied = false;
		gameOver = false;
		gameWon = false;
		pacman = new Pacman();
		ghosts = new ArrayList<>();
		coins = new ArrayList<>();
		addCoins();
		addGhosts();
	}

	@Override
	public void enter(Object memento) {
		active = true;
		deltaTimeAverage = 0;
		player = (PlayerData) memento;
		infoBar.setPlayer(player);
		if (gameOver) {
			resetGame();
		}
		else if (pacmanDied){
			resetRound();
		}
	}

	@Override
	public void processKeyPressed(int aKeyCode) {
		pacman.processKeyPressed(aKeyCode);
	}

	@Override
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);

		if (aKeyCode == KeyEvent.VK_Q)
			active = false;
	}

	@Override
	public void update(long deltaTime) {
		if (gameWon){
			map.nextLevel();
			resetGame();
		}
		else if (gameOver)
			active = false;
		else if (pacmanDied) {
			player.setPlayerLives(player.getPlayerLives()-1);
			if (player.getPlayerLives() == 0)
				gameOver = true;
			else
				resetRound();
		} else {
			deltaTimeAverage = deltaTimeAverage * 0.9f + 0.1f * (float) deltaTime;
			pacman.pacmanMovement(map, deltaTime);
			ghostUpdate(deltaTime);
			ghostsCollision();
			coinsCollision();
		}
	}

	public void ghostUpdate(long deltaTime) {
		for (Ghost boo : ghosts) {
			boo.ghostMovement(map, deltaTime);
		}
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public String next() {
		return "GameOver";
	}

	@Override
	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		infoBar.render(g);
		map.render(g);
		drawPacman(g);
		drawGhosts(g);
		drawCoins(g);
	}

	private void addCoins(){
		int[][] mapGrid = map.getGrid();
		for (int row = 0; row < mapGrid.length; row++) {
			for (int col = 0; col < mapGrid[row].length; col++) {
				if (mapGrid[row][col] == 0){
					if (map.isCoinLocation(row, col)) {
						float x = (col * Map.BLOCK_WIDTH) + (Map.BLOCK_WIDTH / 2) - (Coin.WIDTH / 2f);
						float y = (row * Map.BLOCK_HEIGHT) + InfoBar.HEIGHT + (Map.BLOCK_HEIGHT / 2) - (Coin.HEIGHT / 2f);
						coins.add(new Coin(x, y));
					}
				}
			}
		}
	}

	private void addGhosts(){
		for(int i = 0; i < ghostsNumber; i++)
			ghosts.add(new Ghost(map.getGhostStartX(), map.getGhostStartY()));
	}

	private void ghostsCollision() {
		for (Ghost boo : ghosts) {
			if (boo.checkCollision(pacman))
				pacmanDied = true;
		}
	}

	private void coinsCollision() {
		boolean noMoreCoins = true;
		for (Coin c : coins) {
			if (c.isVisible() && c.checkCollision(pacman))
				player.setPlayerScore(player.getPlayerScore() + Coin.SCORE);
			else if (noMoreCoins && c.isVisible())
				noMoreCoins = false;
		}
		if(noMoreCoins)
			gameWon = true;
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
				g.drawImage(c.getImage(), (int) c.getX(), (int) c.getY(), null);
		}
	}

	private void resetRound(){
		pacmanDied = false;
		pacman.reset();
		resetGhosts();
	}

	private void resetGame(){
		gameWon = false;
		gameOver = false;
		player.resetPlayerData();
		resetCoins();
		resetRound();
	}

	@Override
	public Object memento() {
		return player;
	}
}