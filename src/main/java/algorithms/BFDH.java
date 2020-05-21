package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import structures.Detail;
import structures.Level;
import structures.Plate;


public class BFDH implements PackageAlgorithm {

    public Plate execute(ArrayList<Detail> a, int plate_width) {
        Collections.sort(a, new Detail.CompareByHeight());
        ArrayList<Level> levels = new ArrayList<Level>();
        int n = a.size();
        int plateHeight = a.get(0).getHeight();
        Plate result = new Plate(plate_width);
        Level temp = new Level(0, a.get(0).getHeight(), a.get(0).getWidth(), a.get(0).getHeight());
        result.attach(a.get(0), 0, 0);
        levels.add(temp);
        for (int i = 1; i < n; i++) {
            boolean placed = false;
            Collections.sort(levels, new Level.CompareByWidth());
            for (Level current_level : levels) {
                if (current_level.isSufficient(a.get(i).getWidth(), plate_width) && !placed) {// && (a.get(i).getHeight() <= current_level.getTop() - current_level.getBottom())) {
                    result.attach(a.get(i), current_level.getWidth(), current_level.getBottom());
                    current_level.increaseWidth(a.get(i).getWidth());
                    placed = true;
                }
            }
            if (!placed){
                temp = new Level(plateHeight,
                        plateHeight + a.get(i).getHeight(),
                        a.get(i).getWidth(), a.get(i).getHeight());
                result.attach(a.get(i), 0, plateHeight);
                levels.add(temp);
                if (plateHeight < temp.getTop()) {
                    plateHeight = temp.getTop();
                }
            }
        }
        result.setHeight(plateHeight);
        return result;
    }
}