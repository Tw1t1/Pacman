public class PacmanGame extends Game {
	public enum Direction {UP, DOWN, LEFT, RIGHT, NOTHING};
	public enum Difficulty {EASY, HARD, EXPERT};
	public static final float[] SPEED = {0.1f, 0.15f, 0.2f};
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
		app.setVisible( true );
		app.run();
		System.exit( 0 );
	}
}
