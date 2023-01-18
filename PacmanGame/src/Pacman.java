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

    public void reset() {
        setX(pacmanX);
        setY(pacmanY);
    }

    public Pacman() {
        super(0, 0, 0, 0);
        reset();
        setWidth(pacmanWidth);
        setHeight(pacmanHeight);
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

    // public void pacmanMovement(Map map, long deltaTime) {
    //     float newX = getX();
    //     float newY = getY();

    //     // x:
    //     if (getX() >= 0 && getX() <= Game.WIDTH - pacmanWidth) { // game.width-pacman.width
    //         if (direction == 3) // right movement
    //             newX = (getX() + 0.1f * deltaTime);
    //         else if (direction == 4) // left movement
    //             newX = (getX() - 0.1f * deltaTime);

    //         // x bounds:
    //     } else if (getX() > Game.WIDTH - pacmanWidth) {
    //         if (direction == 4) // right bound
    //             newX = (getX() - 0.1f * deltaTime);
    //     } else if (getX() < 0) {
    //         if (direction == 3) // left bound
    //             newX = (getX() + 0.1f * deltaTime);
    //     }

    //     // y:
    //     if (getY() >= 0 && getY() <= Game.HEIGHT - pacmanHeight) {
    //         if (direction == 1) // up movement
    //             newY = (getY() - 0.1f * deltaTime);
    //         else if (direction == 2) // down movement
    //             newY = (getY() + 0.1f * deltaTime);

    //         // y bounds:
    //     } else if (getY() > Game.HEIGHT - pacmanHeight) {
    //         if (direction == 1) // down bound
    //             newY = (getY() - 0.1f * deltaTime);
    //     } else if (getY() < 0) {
    //         if (direction == 2) // up bound
    //             newY = (getY() + 0.1f * deltaTime);
    //     }

    //     if(map.collision(newX, newY, pacmanWidth, pacmanHeight)){
    //         setX(newX);
    //         setY(newY);
    //     }
    // }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/pacman.png");
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void pacmanMovement(Map map, long deltaTime) {
        float limCol = (Game.HEIGHT - InfoBar.getHeight()) / Map.getGrid().length;
        float limRow = (Game.WIDTH) / Map.getGrid()[0].length;
        int currCol = (int) (getX() / 50);// pixel number each col =50
        int currRow = (int) (getY() - InfoBar.getHeight()) / 50; // pixel number each row =50 // at a matrix location 1,1
        System.out.println(currRow + "," + currCol);
        System.out.println(Map.getGrid()[currRow][currCol]);
        System.out.println("direction = " + direction);

        if (direction == RIGHT) { // 3
            if (Map.getGrid()[currRow][currCol + 1] == 0 && currCol + 1 < limCol) {
                setX(getX() + 0.1f * deltaTime);
                System.out.println("direction = " + direction);
                // if (direction == LEFT) {
                // setX(getX() - 0.1f * deltaTime);
                // } else if (direction == DOWN) {
                // setY(getY() + 0.1f * deltaTime);
                // } else if (direction == UP) {
                // setY(getY() - 0.1f * deltaTime);
                // }
            } else if (direction == LEFT) { // 4
                if (Map.getGrid()[currRow][currCol - 1] == 0 && currCol - 1 > 50) {
                    System.out.println("direction = " + direction);
                    setX(getX() - 0.1f * deltaTime);
                }
            }
            if (direction == LEFT) { // 4
                if (Map.getGrid()[currRow][currCol - 1] == 0 && currCol - 1 > 50) {
                    System.out.println("direction = " + direction);
                    setX(getX() - 0.1f * deltaTime);
                    // if (direction == RIGHT) {
                    // setX(getX() + 0.1f * deltaTime);
                    // } else if (direction == DOWN) {
                    // setY(getY() + 0.1f * deltaTime);
                    // } else if (direction == UP) {
                    // setY(getY() - 0.1f * deltaTime);
                    // }
                }
                // if (direction == DOWN) { // 2
                // if (map.getGrid()[currCol][currRow + 1] == 1 && currRow + 1 <= LimRow) {
                // System.out.println("direction = " + direction);
                // if (direction == LEFT) { // move left
                // setX(getX() - 0.1f * deltaTime);
                // } else if (direction == RIGHT) {
                // setX(getX() + 0.1f * deltaTime);
                // } else if (direction == UP) {
                // setY(getY() - 0.1f * deltaTime);
                // }
                // } else {
                // setY(getY() + 0.1f * deltaTime);
                // }
                // }
                // if (direction == UP) {// 1
                // if (map.getGrid()[currCol][currRow] == 1 && currRow + -1 > 50) {
                // System.out.println("direction = " + direction);
                // if (direction == LEFT) { // move left
                // setX(getX() - 0.1f * deltaTime);
                // } else if (direction == RIGHT) {
                // setX(getX() + 0.1f * deltaTime);
                // } else if (direction == DOWN) {
                // setY(getY() + 0.1f * deltaTime);
                // }
                // } else {
                // setY(getY() - 0.1f * deltaTime);
            }
        }
    }
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