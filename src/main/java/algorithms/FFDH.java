package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import structures.Detail;
import structures.Level;
import structures.Plate;


public class FFDH implements PackageAlgorithm {

    public Plate execute(ArrayList<Detail> a, int plate_width) {
        Collections.sort(a, new Detail.CompareByHeight());
        ArrayList<Level> levels = new ArrayList<Level>();
        int n = a.size();
        Plate result = new Plate(plate_width);
        Level temp = new Level(0, a.get(0).getHeight(), a.get(0).getWidth());
        result.attach(a.get(0), 0, 0);
        levels.add(temp);
        for (int i = 1; i < n; i++){
            boolean placed = false;
            for (Level current_level: levels){
                if (current_level.isSufficient(a.get(i).getWidth(), plate_width) && !placed){
                    result.attach(a.get(i), current_level.getWidth(), current_level.getBottom());
                    current_level.increaseWidth(a.get(i).getWidth());
                    temp = null;
                    placed = true;
                }
                else if (current_level == levels.get(levels.size() - 1) && !placed){
                    temp = new Level(current_level.getTop(),
                            current_level.getTop() + a.get(i).getHeight(),
                            a.get(i).getWidth());
                    result.attach(a.get(i), 0, current_level.getTop());
                    placed = true;
                }
            }
            if (temp!=null) levels.add(temp);
        }
        result.setHeight(levels.get(levels.size()-1).getTop());
        return result;
    }
}

