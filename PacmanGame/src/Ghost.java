import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Ghost extends GameObject {
    private final int ghostWidth = 40;
    private final int ghostHeight = 40;
    private final long directionChangeInterval = 1000; // interval in milliseconds between direction changes
    private long lastDirectionChangeTime; // variable to store the last time the direction was changed

    private int direction;
    private float ghostStartX;
    private float ghostStartY;
    private Random random;


    public Ghost(float x, float y) {
        super(0, 0, 0, 0);
        setStartLocation(x, y);
        random = new Random();
        reset();
        setWidth(ghostWidth);
        setHeight(ghostHeight);
    }

    public void reset() {
        setX(getRandomX()); // get first X
        setY(getRandomY()); // get first Y
    }

    public void ghostMovement(Map map, long deltaTime) {
        float newX = getX();
        float newY = getY();

        if (System.currentTimeMillis() - lastDirectionChangeTime > directionChangeInterval) {
            direction = random.nextInt(4) + 1; // set direction to a random number between 1 and 4
            lastDirectionChangeTime = System.currentTimeMillis();
        }

        // x:
        if (getX() >= 0 && getX() <= Game.WIDTH - ghostWidth) { // game.width-pacman.width
            if (direction == 3) // right movement
                newX = (getX() + 0.1f * deltaTime);
            else if (direction == 4) // left movement
                newX = (getX() - 0.1f * deltaTime);

            // x bounds:
        } else if (getX() > Game.WIDTH - ghostWidth) {
            if (direction == 4) // right bound
                newX = (getX() - 0.1f * deltaTime);
        } else if (getX() < 0) {
            if (direction == 3) // left bound
                newX = (getX() + 0.1f * deltaTime);
        }

        // y:
        if (getY() >= 0 && getY() <= Game.HEIGHT - ghostHeight) {
            if (direction == 1) // up movement
                newY = (getY() - 0.1f * deltaTime);
            else if (direction == 2) // down movement
                newY = (getY() + 0.1f * deltaTime);

            // y bounds:
        } else if (getY() > Game.HEIGHT - ghostHeight) {
            if (direction == 1) // down bound
                newY = (getY() - 0.1f * deltaTime);
        } else if (getY() < 0) {
            if (direction == 2) // up bound
                newY = (getY() + 0.1f * deltaTime);
        }

        if(map.collision(newX, newY, ghostWidth, ghostHeight)){
            setX(newX);
            setY(newY);
        }
    }

    public void setStartLocation(float x, float y) {
        ghostStartX = x;
        ghostStartY = y;
    }

    private float getRandomX(){
        return ghostStartX + random.nextInt((int)(3 * Map.BLOCK_WIDTH) - ghostWidth);
    }

    private float getRandomY(){
        return ghostStartY + random.nextInt((int)(2 * Map.BLOCK_HEIGHT) - ghostHeight);
    }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pinkGhost.png");
    }

}
