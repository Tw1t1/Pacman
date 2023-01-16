import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Toolkit;

public class Pacman extends GameObject {
    private int direction;
    private final int UP = 1, DOWN = 2, LEFT = 4, RIGHT = 3;
    private final int pacmanWidth = 40;
    private final int pacmanHeight = 40;
    private final float pacmanX = (11 * Map.BLOCK_WIDTH) + (Map.BLOCK_WIDTH / 2f) - (pacmanWidth / 2f);
    private final float pacmanY = (13 * Map.BLOCK_HEIGHT) + InfoBar.HEIGHT + (Map.BLOCK_HEIGHT / 2f) - (pacmanHeight / 2f);


    public Pacman() {
        super(0, 0, 0, 0);
        reset();
        setWidth(pacmanWidth);
        setHeight(pacmanHeight);
    }

    public void reset() {
        setX(pacmanX);
        setY(pacmanY);
    }

    public void processKeyPressed(int aKeyCode) {
        if (aKeyCode == KeyEvent.VK_UP) // move up
            setDirection(UP);

        if (aKeyCode == KeyEvent.VK_DOWN) // move down
            setDirection(DOWN);

        if (aKeyCode == KeyEvent.VK_RIGHT) // move right
            setDirection(RIGHT);

        if (aKeyCode == KeyEvent.VK_LEFT) // move left
            setDirection(LEFT);
    }

    public void pacmanMovement(Map map, long deltaTime) {
        float newX = getX();
        float newY = getY();

        // x:
        if (getX() >= 0 && getX() <= Game.WIDTH - pacmanWidth) { // game.width-pacman.width
            if (direction == 3) // right movement
                newX = (getX() + 0.1f * deltaTime);
            else if (direction == 4) // left movement
                newX = (getX() - 0.1f * deltaTime);

            // x bounds:
        } else if (getX() > Game.WIDTH - pacmanWidth) {
            if (direction == 4) // right bound
                newX = (getX() - 0.1f * deltaTime);
        } else if (getX() < 0) {
            if (direction == 3) // left bound
                newX = (getX() + 0.1f * deltaTime);
        }

        // y:
        if (getY() >= 0 && getY() <= Game.HEIGHT - pacmanHeight) {
            if (direction == 1) // up movement
                newY = (getY() - 0.1f * deltaTime);
            else if (direction == 2) // down movement
                newY = (getY() + 0.1f * deltaTime);

            // y bounds:
        } else if (getY() > Game.HEIGHT - pacmanHeight) {
            if (direction == 1) // down bound
                newY = (getY() - 0.1f * deltaTime);
        } else if (getY() < 0) {
            if (direction == 2) // up bound
                newY = (getY() + 0.1f * deltaTime);
        }

        if(map.collision(newX, newY, pacmanWidth, pacmanHeight)){
            setX(newX);
            setY(newY);
        }
    }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pacman.png");
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

}