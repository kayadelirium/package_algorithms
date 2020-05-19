package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import structures.Detail;
import structures.Plate;


public class NFDH implements PackageAlgorithm {

    public Plate execute(ArrayList<Detail> a, int plate_width) {
        Collections.sort(a, new Detail.CompareByHeight());
        int level_height, level_width = 0, n = a.size();
        Plate result = new Plate(plate_width);
        result.attach(a.get(0), 0, 0);
        int level_bottom = 0;
        level_height = a.get(0).getHeight();
        level_width += a.get(0).getWidth();
        for (int i = 1; i < n; i++){
            if (result.getWidth() - level_width >= a.get(i).getWidth()){
                result.attach(a.get(i), level_width, level_bottom);
                level_width += a.get(i).getWidth();
            }
            else{
                result.attach(a.get(i), 0, level_height);
                level_width = a.get(i).getWidth();
                level_bottom = level_height;
                level_height += a.get(i).getHeight();
            }
        }
        result.setHeight(level_height);
        return result;
    }
}
