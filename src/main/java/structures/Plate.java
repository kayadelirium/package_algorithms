package structures;

import java.io.IOException;
import java.util.ArrayList;

public class Plate {

    private int width;
    private int height;
    private ArrayList<Detail> list;
    // какие-то свойства листа пока не знаю чего-то там

    public Plate(int width){
        list = new ArrayList<Detail>();
        this.width = width;
    }

    public ArrayList<Detail> getList() {
        return list;
    }

    public void attach(Detail d, int x, int y)
    {
        d.attachTo(x,y);
        list.add(d);
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    @Override
    public String toString() {
        return "Plate{" +
                "width=" + width +
                ", height=" + height +
                ", list=" + list +
                '}';
    }

    public int emptySpaces(){
        int result = getWidth()*getHeight();
        for (Detail d: getList())
        {
            result-= d.getWidth()*d.getHeight();
        }
        result = (100*result)/(getWidth()*getHeight());
        return result;
    }
}
