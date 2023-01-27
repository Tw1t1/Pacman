public class PacmanGame extends Game {
	public enum Direction {UP, DOWN, LEFT, RIGHT, NOTHING};
	public static final float[] SPEED = {0.1f, 0.15f, 0.2f}; // used to set difficulty
	static final int STARTING_LIVES = 3;
	public PacmanGame() {
		GameState welcome = new WelcomeState();
		GameState play = new PlayState();
		GameState gameOver = new GameOverState();
		GameState gameWon = new GameWonState();
		stateMachine.installState("Welcome", welcome);
		stateMachine.installState("Play", play);
		stateMachine.installState("GameWon", gameWon);
		stateMachine.installState("GameOver", gameOver);
		stateMachine.setStartState(welcome);
	}

	public static void main( String[] args ) {
		Game app = new PacmanGame();
		app.setTitle( "Pacman Game" );
		app.setResizable(false);
		app.setVisible( true );
		app.setLocationRelativeTo(null);
		app.run();
		System.exit( 0 );
	}
}
