public class Coin extends GameObject{

    private final int coinScore = 100;
    private final int coinWidth = 10;
    private final int coinHeight = 10;
    private boolean visible;

    public Coin(float x, float y) {
        super(x, y, 0, 0);
        setWidth(coinWidth);
        setHeight(coinHeight);
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

    public int coinScore(){
        return coinScore;
    }
}