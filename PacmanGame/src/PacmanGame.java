
public class PacmanGame extends Game {

	public PacmanGame() {
		GameState welcome = new WelcomeState();
		GameState play = new PlayState();
		stateMachine.installState("Play", play);
		stateMachine.installState("Welcome", welcome);
		stateMachine.setStartState(welcome);
	}
	
	public static void main( String[] args ) {
	    Game app = new PacmanGame();
	    app.setTitle( "A Simple Game" );
	    app.setVisible( true );
	    app.run();
	    System.exit( 0 );
	  }
}
