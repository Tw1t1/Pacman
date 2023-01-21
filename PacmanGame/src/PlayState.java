import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends GameState {
	private final int ghostsNumber = 5;
	private boolean active;
	private boolean pacmanDied;
	private boolean gameOver;
	private boolean roundWon;
	private boolean gameWon;
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
		roundWon = false;
		pacman = new Pacman();
		ghosts = new ArrayList<>();
		coins = new ArrayList<>();
		addCoins();
		addGhosts();
	}

	@Override
	public void enter(Object memento) {
		active = true;
		player = (PlayerData) memento;
		infoBar.setPlayer(player);
		if (gameOver || gameWon) {
			resetGame();
		}
		else if (pacmanDied){
			resetRound();
		}
	}

	@Override
	public void processKeyPressed(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_UP) // move up
			pacman.setCurrentDirection(PacmanGame.Direction.UP);

		if (aKeyCode == KeyEvent.VK_DOWN) // move down
			pacman.setCurrentDirection(PacmanGame.Direction.DOWN);

		if (aKeyCode == KeyEvent.VK_RIGHT) // move right
			pacman.setCurrentDirection(PacmanGame.Direction.RIGHT);

		if (aKeyCode == KeyEvent.VK_LEFT) // move left
			pacman.setCurrentDirection(PacmanGame.Direction.LEFT);
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
		if (roundWon){ // no more coins
			if(!map.nextLevel()){
				active = false;
				gameWon = true;
			}
			else
				resetGame();
		}
		else if (gameOver){
			active = false;
			Map.setLevel(0);
		}
		else if (pacmanDied) {
			player.setPlayerLives(player.getPlayerLives()-1);
			if (player.getPlayerLives() == 0)
				gameOver = true;
			else
				resetRound();
		} else {
			pacman.pacmanMovement(deltaTime);
			ghostUpdate(deltaTime);
			ghostsCollision();
			coinsCollision();
		}
	}

	private void ghostUpdate(long deltaTime) {
		for (Ghost boo : ghosts) {
			boo.ghostMovement(deltaTime);
		}
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public String next() {
		if(gameWon) {
			return "GameWon";
		}
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
		int[][] mapGrid = Map.getGrid();
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
			roundWon = true;
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
		if(gameOver || gameWon) {
			player.resetPlayerData();
		}
		roundWon = false;
		gameOver = false;
		gameWon = false;
		coins = new ArrayList<>();
		addCoins();
		resetRound();
	}

	@Override
	public Object memento() {
		return player;
	}
}