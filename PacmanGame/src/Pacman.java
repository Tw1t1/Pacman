import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;

public class Pacman extends GameObject {
    private int direction;
    private final int UP = 1, DOWN = 2, LEFT = 4, RIGHT = 3;
    private final float pacmanX = 0;
    private final float pacmanY = 100;
    private final int pacmanWidth = 25;
    private final int pacmanHeight = 29;

    public Pacman() {
        super(0, 0, 0, 0);
        reset();
    }

    public void reset() {
        setX(pacmanX);
        setY(pacmanY);
        setWidth(pacmanWidth);
        setHeight(pacmanHeight);
    }

    public void processKeyReleased(int aKeyCode) {
        if (aKeyCode == KeyEvent.VK_UP) // move up
            setDirection(UP);

        if (aKeyCode == KeyEvent.VK_DOWN) // move down
            setDirection(DOWN);

        if (aKeyCode == KeyEvent.VK_RIGHT) // move right
            setDirection(RIGHT);

        if (aKeyCode == KeyEvent.VK_LEFT) // move left
            setDirection(LEFT);
    }

    public void pacmanMovement(long deltaTime) {

        if (getX() >= 0 && getX() <= Game.WIDTH - pacmanWidth) { // game.width-pacman.width
            if (direction == 3) { // right movement
                setX(getX() + 0.1f * deltaTime);
            } else if (direction == 4) // left movment
                setX(getX() - 0.1f * deltaTime);
            // x bounds:
        } else if (getX() > Game.WIDTH - pacmanWidth) {
            if (direction == 4) // right bound
                setX(getX() - 0.1f * deltaTime);
        } else if (getX() < 0) {
            if (direction == 3) { // left bound
                setX(getX() + 0.1f * deltaTime);
            }
        }
        if (getY() >= 0 && getY() <= Game.HEIGHT - pacmanHeight) {
            if (direction == 1) // up movment
                setY(getY() - 0.1f * deltaTime);
            else if (direction == 2) // down movement
                setY(getY() + 0.1f * deltaTime);

            // y bounds:
        } else if (getY() > Game.HEIGHT - pacmanHeight) {
            if (direction == 1) { // down bound
                setY(getY() - 0.1f * deltaTime);
            }
        } else if (getY() < 0) {
            if (direction == 2) { // up bound
                setY(getY() + 0.1f * deltaTime);
            }
        }
    }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/src/utils/pcman.png");
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}