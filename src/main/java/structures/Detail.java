package structures;

import java.util.Collection;
import java.util.Comparator;

public class Detail {
    private int id;
    private int width;
    private int height;
    private int x;
    private int y;
    public Detail(int id, int width, int height){
        this.id = id;
        this.height = height;
        this.width = width;
        this.x = 0;
        this.y = 0;
    }
    public static class CompareByHeight implements Comparator<Detail> {

        public int compare(Detail o1, Detail o2) {
            return o2.getHeight() - o1.getHeight();
        }
    }

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "Detail{" +
                "width=" + width +
                ", height=" + height +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}

