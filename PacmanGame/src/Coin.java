import java.awt.*;

public class Coin extends GameObject{

    static final int SCORE = 100;
    static final int WIDTH = 20;
    static final int HEIGHT = 20;
    private boolean visible;

    public Coin(float x, float y) {
        super(x, y, 0, 0);
        setWidth(WIDTH);
        setHeight(HEIGHT);
        setVisible(true);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean checkCollision(Pacman p){
        if(checkCollision1D(getX(), getWidth(), p.getX(), p.getWidth()) && checkCollision1D(getY(), getHeight(), p.getY(), p.getHeight())){
            setVisible(false);
            return true;
        }
        return false;
    }

    public Image getImage() {
        return Toolkit.getDefaultToolkit().getImage("PacmanGame/utils/coin.png");
    }
}