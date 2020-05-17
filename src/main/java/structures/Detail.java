package structures;

public class Detail {
    private int width;
    private int height;
    private int x;
    private int y;
    public Detail(int width, int height){
        this.height = height;
        this.width = width;
        this.x = 0;
        this.y = 0;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void attachTo(int x, int y)
    {
        setX(x);
        setY(y);
    }
}

