package structures;

import java.util.Comparator;

public class Level {
    private int bottom;
    private int top;
    private int width;
    private int height;
    private int topWidth;

    public Level (int bottom, int top, int width, int height){
        setBottom(bottom);
        setTop(top);
        setWidth(width);
        setHeight(height);
    }

    public Level (int bottom, int top, int width, int height, int topWidth){
        setBottom(bottom);
        setTop(top);
        setWidth(width);
        setHeight(height);
        setTopWidth(topWidth);
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

    public void setHeight(int height){
        this.height = height;
    }

    public void setTopWidth(int topWidth) {
        this.topWidth = topWidth;
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

    public int getHeight(){
        return this.height;
    }

    public int getTopWidth() {
        return topWidth;
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

    public void increaseTopWidth(int inc) { this.topWidth -= inc; }
    // (x,y) -> (plate_width - det.width, level.top - det.height)
    // (plate_width - level.topwidth, ///)

    public static class CompareByWidth implements Comparator<Level> {
        public int compare(Level o1, Level o2) {
            return o2.getWidth() - o1.getWidth();
        }
    }

    public static class CompareByTopWidth implements Comparator<Level> {
        public int compare(Level o1, Level o2) {
            return o1.getTopWidth() - o2.getTopWidth();
        }
    }
}
