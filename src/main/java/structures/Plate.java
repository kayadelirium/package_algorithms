package structures;

import java.io.IOException;
import java.util.ArrayList;

public class Plate {

    private int width;
    private int height;
    private ArrayList<Detail> list;
    // какие-то свойства листа пока не знаю чего-то там

    public Plate(int width, int height){
        list = new ArrayList<Detail>();
        this.width = width;
        this.height = height;
    }

    public ArrayList<Detail> getList() {
        return list;
    }

    public void attach(Detail d, int x, int y)
    {
        d.attachTo(x,y);
        list.add(d);
    }

}
