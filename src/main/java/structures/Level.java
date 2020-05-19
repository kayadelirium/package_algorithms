package structures;

public class Level {
    private int bottom;
    private int top;
    private int width;

    public Level (int bottom, int top, int width){
        setBottom(bottom);
        setTop(top);
        setWidth(width);
    }

    public void setBottom(int bottom){
        this.bottom = bottom;
    }

    public void setTop(int top){
        this.top = top;
    }

    public void setWidth(int width){
        this.width = width;
    }

    public int getBottom(){
        return this.bottom;
    }

    public int getTop(){
        return this.top;
    }

    public int getWidth(){
        return this.width;
    }

    public int remainingPlace(int space){
        return space - this.width;
    }

    public boolean isSufficient(int needed, int space){
        return remainingPlace(space) - needed > 0;
    }

    public void increaseWidth(int inc){
        this.width += inc;
    }
}

