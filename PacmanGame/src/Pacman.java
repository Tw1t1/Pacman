import java.awt.Image;
import java.awt.Toolkit;

public class Pacman extends GameObject {
    private PacmanGame.Direction direction;
    private final int pacmanWidth = 40;
    private final int pacmanHeight = 40;
    private final float pacmanX = (11 * Map.BLOCK_WIDTH) + (Map.BLOCK_WIDTH / 2f) - (pacmanWidth / 2f);
    private final float pacmanY = (13 * Map.BLOCK_HEIGHT) + InfoBar.HEIGHT + (Map.BLOCK_HEIGHT / 2f) - (pacmanHeight / 2f);


    public Pacman() {
        super(0, 0, 0, 0);
        reset();
        setWidth(pacmanWidth);
        setHeight(pacmanHeight);
        direction = PacmanGame.Direction.NOTHING;
    }

    public void reset() {
        setX(pacmanX);
        setY(pacmanY);
    }

     public void pacmanMovement(long deltaTime) {
        float nextX = getX();
        float nextY = getY();
        switch(direction){
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
        switch(direction){
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
        this.direction = currentDirection;
    }

//    public void pacmanMovement(Map map, long deltaTime) {
//        float limCol = (Game.HEIGHT - InfoBar.getHeight()) / Map.getGrid().length;
//        float limRow = (Game.WIDTH) / Map.getGrid()[0].length;
//        int currCol = (int) (getX() / 50);// pixel number each col =50
//        int currRow = (int) (getY() - InfoBar.getHeight()) / 50; // pixel number each row =50 // at a matrix location 1,1
//        System.out.println(currRow + "," + currCol);
//        System.out.println(Map.getGrid()[currRow][currCol]);
//        System.out.println("direction = " + direction);
//
//        if (direction == RIGHT) { // 3
//            if (Map.getGrid()[currRow][currCol + 1] == 0 && currCol + 1 < limCol) {
//                setX(getX() + 0.1f * deltaTime);
//                System.out.println("direction = " + direction);
//                // if (direction == LEFT) {
//                // setX(getX() - 0.1f * deltaTime);
//                // } else if (direction == DOWN) {
//                // setY(getY() + 0.1f * deltaTime);
//                // } else if (direction == UP) {
//                // setY(getY() - 0.1f * deltaTime);
//                // }
//            } else if (direction == LEFT) { // 4
//                if (Map.getGrid()[currRow][currCol - 1] == 0 && currCol - 1 > 50) {
//                    System.out.println("direction = " + direction);
//                    setX(getX() - 0.1f * deltaTime);
//                }
//            }
//            if (direction == LEFT) { // 4
//                if (Map.getGrid()[currRow][currCol - 1] == 0 && currCol - 1 > 50) {
//                    System.out.println("direction = " + direction);
//                    setX(getX() - 0.1f * deltaTime);
//                    // if (direction == RIGHT) {
//                    // setX(getX() + 0.1f * deltaTime);
//                    // } else if (direction == DOWN) {
//                    // setY(getY() + 0.1f * deltaTime);
//                    // } else if (direction == UP) {
//                    // setY(getY() - 0.1f * deltaTime);
//                    // }
//                }
//                // if (direction == DOWN) { // 2
//                // if (map.getGrid()[currCol][currRow + 1] == 1 && currRow + 1 <= LimRow) {
//                // System.out.println("direction = " + direction);
//                // if (direction == LEFT) { // move left
//                // setX(getX() - 0.1f * deltaTime);
//                // } else if (direction == RIGHT) {
//                // setX(getX() + 0.1f * deltaTime);
//                // } else if (direction == UP) {
//                // setY(getY() - 0.1f * deltaTime);
//                // }
//                // } else {
//                // setY(getY() + 0.1f * deltaTime);
//                // }
//                // }
//                // if (direction == UP) {// 1
//                // if (map.getGrid()[currCol][currRow] == 1 && currRow + -1 > 50) {
//                // System.out.println("direction = " + direction);
//                // if (direction == LEFT) { // move left
//                // setX(getX() - 0.1f * deltaTime);
//                // } else if (direction == RIGHT) {
//                // setX(getX() + 0.1f * deltaTime);
//                // } else if (direction == DOWN) {
//                // setY(getY() + 0.1f * deltaTime);
//                // }
//                // } else {
//                // setY(getY() - 0.1f * deltaTime);
//            }
//        }
//    }
    // map.getGrid()[currCol-1][currRow]==1 && currCol-1>=50

    // if (map.getGrid()[currCol + 1][currRow] == 1 && currCol + 1 <= LimCol) { //
    // move all direct
    // if (direction == 3) { // move right
    // setX(getX() + 0.1f * deltaTime);
    // }
    // if (direction == 4) { // move left
    // setX(getX() - 0.1f * deltaTime);
    // } else if (direction == 2) {
    // setY(getY() + 0.1f * deltaTime);
    // } else if (direction == 1) {
    // setY(getY() - 0.1f * deltaTime);
    // }
    // priviosDircation = direction;
    // } else if (map.getGrid()[currCol][currRow + 1] == 0 && currRow + 1 <= LimRow)
    // {
    // if (direction == 3) { // move right
    // setX(getX() + 0.1f * deltaTime);
    // } else if (direction == 4) { // move left
    // setX(getX() - 0.1f * deltaTime);
    // } else if (direction == 2) {
    // setY(getY() + 0.1f * deltaTime);
    // } else if (direction == 1) {
    // setY(getY() - 0.1f * deltaTime);
    // }
    // priviosDircation = direction;
    // } else {
    // priviosDircation = direction;
    // }
    // if(map.getGrid())[currCol+1][currRow]==1) ||
    // if(map.getGrid()[currCol][CurrRow+1]==1)
    // need to redirect
    // else continue privousDirection

}