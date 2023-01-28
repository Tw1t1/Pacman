import java.awt.Image;
import java.awt.Toolkit;

public class Pacman extends GameObject {
    private PacmanGame.Direction direction;
    private final int pacmanWidth = 40;
    private final int pacmanHeight = 40;
    private final float pacmanX = (11 * Map.BLOCK_WIDTH) + ((Map.BLOCK_WIDTH - pacmanWidth) / 2f);
    private final float pacmanY = (13 * Map.BLOCK_HEIGHT) + InfoBar.HEIGHT + ((Map.BLOCK_HEIGHT - pacmanHeight) / 2f);

    public Pacman() {
        super(0, 0, 0, 0);
        reset();
        setWidth(pacmanWidth);
        setHeight(pacmanHeight);
        direction = PacmanGame.Direction.NOTHING;
    }

    public void reset() {
        setDirection(PacmanGame.Direction.NOTHING);
        setX(pacmanX);
        setY(pacmanY);
    }

    public PacmanGame.Direction getDirection() {
        return direction;
    }

    public void setDirection(PacmanGame.Direction direction) {
        this.direction = direction;
    }

     public void pacmanMovement(long deltaTime) {
        float nextX = getX();
        float nextY = getY();
        switch(getDirection()){
            case RIGHT:
                nextX += PacmanGame.SPEED[0] * deltaTime;
                break;
            case LEFT:
                nextX -= PacmanGame.SPEED[0] * deltaTime;
                break;
            case DOWN:
                nextY += PacmanGame.SPEED[0] * deltaTime;
                break;
            case UP:
                nextY -= PacmanGame.SPEED[0] * deltaTime;
                break;
            default:
                break;
        }
        if(Map.collision(nextX, nextY, pacmanWidth, pacmanHeight)){
            setX(nextX);
            setY(nextY);
        }
     }

    public Image getImage() {
        switch(getDirection()){
            case UP:
                return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pacman_up.png");
            case DOWN:
                return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pacman_down.png");
            case LEFT:
                return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pacman_left.png");
            default:
                return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pacman_right.png");
        }
    }

    public void setCurrentDirection(PacmanGame.Direction currentDirection) {
        float space = 10;
        switch(currentDirection){
            case RIGHT:
                if(Map.collision(getX() + space, getY(), pacmanWidth, pacmanHeight))
                    break;
                return;
            case LEFT:
                if(Map.collision(getX() - space, getY(), pacmanWidth, pacmanHeight))
                    break;
                return;
            case DOWN:
                if(Map.collision(getX(), getY() + space, pacmanWidth, pacmanHeight))
                    break;
                return;
            case UP:
                if(Map.collision(getX(), getY() - space, pacmanWidth, pacmanHeight))
                    break;
                return;
            default:
                break;
        }
        setDirection(currentDirection);
    }
}