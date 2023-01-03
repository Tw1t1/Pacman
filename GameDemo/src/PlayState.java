import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class PlayState extends GameState {

	boolean active;
	float x;
	float deltaTimeAverage;
	
	String message;
	
	public PlayState() {
		x = 0;
	}
	
	public void enter(Object memento) {
		active = true;
		deltaTimeAverage = 0;
	}
	
	public void processKeyReleased(int aKeyCode) {
		if (aKeyCode == KeyEvent.VK_ESCAPE)
			System.exit(0);
		
		if (aKeyCode == KeyEvent.VK_Q)
			active = false;
			
	}
	
	public void update(long deltaTime) {
		deltaTimeAverage = deltaTimeAverage* 0.9f + 0.1f*(float)deltaTime;
		x = x + 0.1f*deltaTime;
		if (x > 640)
			x = x - 640;
	}
	
	public boolean isActive() { return active; }
	
	public String next() {
		return "Welcome";
	}

	public void render(GameFrameBuffer aGameFrameBuffer) {
		Graphics g = aGameFrameBuffer.graphics();
		
		g.setColor(Color.white);
		g.drawOval((int)x, 100, 10, 10);
		message = "" + (int)deltaTimeAverage;
		g.drawString(message, 10, 10);

	}

}
