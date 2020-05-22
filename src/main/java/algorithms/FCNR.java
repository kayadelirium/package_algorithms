package algorithms;

import java.util.ArrayList;
import java.util.Collections;

import structures.Detail;
import structures.Level;
import structures.Plate;


public class FCNR implements PackageAlgorithm {

    boolean isPossible (int x, int y, Level level, Plate plate){
        for (Detail currentDetail: plate.getList()){
            if (currentDetail.getY() == level.getBottom()){
                if (x >= currentDetail.getX() && x <= currentDetail.getX() + currentDetail.getWidth()){
                    if (y >= level.getBottom() + currentDetail.getHeight())
                        return true;
                }
            }
        }
        return false;
    }

    public Plate execute(ArrayList<Detail> a, int plate_width) {
        Collections.sort(a, new Detail.CompareByHeight());
        ArrayList<Level> levels = new ArrayList<Level>();
        int n = a.size();
        int plateHeight = a.get(0).getHeight();
        Plate result = new Plate(plate_width);
        Level temp = new Level(0, a.get(0).getHeight(), a.get(0).getWidth(), a.get(0).getHeight(), plate_width);
        result.attach(a.get(0), 0, 0);
        levels.add(temp);
        int tryX, tryY;
        for (int i = 1; i < n; i++) {
            boolean placed = false;
            Collections.sort(levels, new Level.CompareByWidth());
            for (Level current_level : levels) {
                if (current_level.isSufficient(a.get(i).getWidth(), plate_width) && !placed) {// && (a.get(i).getHeight() <= current_level.getTop() - current_level.getBottom())) {
                    result.attach(a.get(i), current_level.getWidth(), current_level.getBottom());
                    current_level.increaseWidth(a.get(i).getWidth());
                    placed = true;
                } /*else {
                    tryX = current_level.getTopWidth() - a.get(i).getWidth();
                    tryY = current_level.getTop() - a.get(i).getHeight();
                    if (isPossible(tryX, tryY, current_level, result) && !placed) {
                        result.attach(a.get(i), tryX, tryY);
                        current_level.increaseTopWidth(a.get(i).getWidth());
                        placed = true;
                    }
                }*/
            }
            Collections.sort(levels, new Level.CompareByTopWidth());
            for (Level current_level : levels) {
                tryX = current_level.getTopWidth() - a.get(i).getWidth();
                tryY = current_level.getTop() - a.get(i).getHeight();
                if (isPossible(tryX, tryY, current_level, result) && !placed) {
                    result.attach(a.get(i), tryX, tryY);
                    current_level.increaseTopWidth(a.get(i).getWidth());
                    placed = true;
                }
            }
            if (!placed) {
                temp = new Level(plateHeight,
                        plateHeight + a.get(i).getHeight(),
                        a.get(i).getWidth(), a.get(i).getHeight(),
                        plate_width);
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

    public String getName() {
        return "FCNR";
    }
}