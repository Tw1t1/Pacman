import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Ghost extends GameObject {
    private final int ghostWidth = 40;
    private final int ghostHeight = 40;
    private final long directionChangeInterval = 1000; // interval in milliseconds between direction changes
    private long lastDirectionChangeTime; // variable to store the last time the direction was changed
    private PacmanGame.Direction direction;
    private float ghostStartX;
    private float ghostStartY;
    private static float ghostSpeed;
    private Random random;


    public Ghost(float x, float y) {
        super(0, 0, 0, 0);
        setStartLocation(x, y);
        random = new Random();
        reset();
        setWidth(ghostWidth);
        setHeight(ghostHeight);
        ghostSpeed = PacmanGame.SPEED[0];
    }

    public void reset() {
        setX(getRandomX()); // get first X
        setY(getRandomY()); // get first Y
    }

    public void ghostMovement(long deltaTime) {
        float nextX = getX();
        float nextY = getY();

        if (System.currentTimeMillis() - lastDirectionChangeTime > directionChangeInterval) {
            while(!setCurrentDirection(PacmanGame.Direction.values()[random.nextInt(4)]));
            lastDirectionChangeTime = System.currentTimeMillis();
        }

        switch(direction){
            case RIGHT:
                nextX += ghostSpeed * deltaTime;
                break;
            case LEFT:
                nextX -= ghostSpeed * deltaTime;
                break;
            case DOWN:
                nextY += ghostSpeed * deltaTime;
                break;
            case UP:
                nextY -= ghostSpeed * deltaTime;
                break;
            default:
                break;
        }

        if(Map.collision(nextX, nextY, ghostWidth, ghostHeight)){
            setX(nextX);
            setY(nextY);
        }
    }

    private boolean setCurrentDirection(PacmanGame.Direction currentDirection) {
        float space = 10;
        switch(currentDirection){
            case RIGHT:
                if(Map.collision(getX() + space, getY(), ghostWidth, ghostHeight))
                    break;
                return false;
            case LEFT:
                if(Map.collision(getX() - space, getY(), ghostWidth, ghostHeight))
                    break;
                return false;
            case DOWN:
                if(Map.collision(getX(), getY() + space, ghostWidth, ghostHeight))
                    break;
                return false;
            case UP:
                if(Map.collision(getX(), getY() - space, ghostWidth, ghostHeight))
                    break;
                return false;
            default:
                break;
        }
        this.direction = currentDirection;
        return true;
    }

    /*
    * SPEED[0] = Easy
    * SPEED[1] = Hard
    * SPEED[2] = Expert
    */
    public static void setGhostSpeed(int difficulty) {
        ghostSpeed = PacmanGame.SPEED[difficulty % PacmanGame.SPEED.length];
    }

    public void setStartLocation(float x, float y) {
        ghostStartX = x;
        ghostStartY = y;
    }

    private float getRandomX(){
        return ghostStartX + random.nextFloat() * ((3 * Map.BLOCK_WIDTH) - ghostWidth);
    }

    private float getRandomY(){
        return ghostStartY + random.nextFloat() * ((2 * Map.BLOCK_HEIGHT) - ghostHeight);
    }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pinkGhost.png");
    }
}
