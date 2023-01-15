public class PacmanGame extends Game {

	public PacmanGame() {
		GameState welcome = new WelcomeState();
		GameState play = new PlayState();
		GameState gameover = new GameoverState();
		stateMachine.installState("Welcome", welcome);
		stateMachine.installState("Play", play);
		stateMachine.installState("Gameover", gameover);
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
