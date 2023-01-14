import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Ghost extends GameObject {
    private int direction;
    private Random random = new Random();
    private final float ghostX = 0;
    private final float ghostY = 100;
    private final int ghostWidth = 32;
    private final int ghostHeight = 31;
    private long lastDirectionChangeTime; // variable to store the last time the direction was changed
    private final long directionChangeInterval = 1000; // interval in milliseconds between direction changes

    public Ghost(float x, float y) {
        super(x, y, 0, 0);
        setWidth(ghostWidth);
        setHeight(ghostHeight);
    }

    public void reset() {
        setX(ghostX); // get first X
        setY(ghostY); // get first Y
    }

    public void ghostMovement(long deltaTime) {
        if (System.currentTimeMillis() - lastDirectionChangeTime > directionChangeInterval) {
            direction = random.nextInt(4) + 1; // set direction to a random number between 1 and 4
            lastDirectionChangeTime = System.currentTimeMillis();
        }
        if (getX() >= 0 && getX() <= Game.WIDTH - ghostWidth) {
            if (direction == 3) { // right movement
                setX(getX() + 0.1f * deltaTime);
            } else if (direction == 4) // left movment
                setX(getX() - 0.1f * deltaTime);
            // x bounds:
        } else if (getX() > Game.WIDTH - ghostWidth) {
            if (direction == 4) // right bound
                setX(getX() - 0.1f * deltaTime);
        } else if (getX() < 0) {
            if (direction == 3) { // left bound
                setX(getX() + 0.1f * deltaTime);
            }
        }
        if (getY() >= 0 && getY() <= Game.HEIGHT - ghostHeight) {
            if (direction == 1) // up movment
                setY(getY() - 0.1f * deltaTime);
            else if (direction == 2) // down movement
                setY(getY() + 0.1f * deltaTime);

            // y bounds:
        } else if (getY() > Game.HEIGHT - ghostHeight) {
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
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/src/utils/redGhost.png");
    }

}
