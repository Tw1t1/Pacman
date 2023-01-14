import java.util.ArrayList;

public class GameObject {
    private float x;
    private float y;
    private int width;
    private int height;

    public GameObject(float x, float y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        if(0 <= x)
            this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        if(0 <= y)
            this.y = y;
    }

    public int getWidth() {
        return width;
    }

    protected void setWidth(int width) {
        if(0 < width)
            this.width = width;
    }

    public int getHeight() {
        return height;
    }

    protected void setHeight(int height) {
        if(0 < height)
            this.height = height;
    }

    public boolean checkCollision(ArrayList<GameObject> l){
        for (GameObject o : l){
            if (checkCollision(o))
                return true;
        }
        return false;
    }

    public boolean checkCollision(GameObject o){
        return checkCollision1D(getX(), getWidth(), o.getX(), o.getWidth()) && checkCollision1D(getY(), getHeight(), o.getY(), o.getHeight());
    }

    protected boolean checkCollision1D(float x1, int length1, float x2, int length2){
        if (x1 < x2)
            return (x2 <= x1 + length1);
        else
            return (x1 <= x2 + length2);
    }
}