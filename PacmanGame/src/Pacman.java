public class Pacman extends GameObject{

    private final float pacmanX = 0;
    private final float pacmanY = 100;
    private final int pacmanWidth = 50;
    private final int pacmanHeight = 50;

    public Pacman() {
        super(0, 0, 0, 0);
        reset();
    }

    public void reset(){
        setX(pacmanX);
        setY(pacmanY);
        setWidth(pacmanWidth);
        setHeight(pacmanHeight);
    }

    public boolean shrink() {
        int newWidth = (int)(getWidth() * 0.95f);
        int newHeight = (int)(getHeight() * 0.95f);

        if(newWidth == 0 || newHeight == 0)
            return false;

        setX(getX() + ((getWidth() - newWidth) / 2f));
        setY(getY() + ((getHeight() - newHeight) / 2f));
        setWidth(newWidth);
        setHeight(newHeight);

        return true;
    }
}