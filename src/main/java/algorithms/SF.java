package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import structures.Detail;
import structures.Level;
import structures.Plate;


public class SF implements PackageAlgorithm {

    public static int count = 0;

    public void FFDH(List<Detail> a, Plate result, ArrayList<Level> levels){
        Collections.sort(a, new Detail.CompareByHeight());
        int n = a.size();
        int begin = 0;
        Level temp = null;
        if (levels.isEmpty()) {
            temp = new Level(0, a.get(0).getHeight(), a.get(0).getWidth(), a.get(0).getHeight());
            result.attach(a.get(0), 0, 0);
            levels.add(temp);
            begin = 1;
        }
        for (int i = begin; i < n; i++){
            boolean placed = false;
            for (Level current_level: levels){
                if (current_level.isSufficient(a.get(i).getWidth(), result.getWidth()) && !placed && (a.get(i).getHeight() <= current_level.getTop() - current_level.getBottom())){
                    result.attach(a.get(i), current_level.getWidth(), current_level.getBottom());
                    count++;
                    current_level.increaseWidth(a.get(i).getWidth());
                    temp = null;
                    placed = true;
                }
                else if (current_level == levels.get(levels.size() - 1) && !placed){
                    temp = new Level(current_level.getTop(),
                            current_level.getTop() + a.get(i).getHeight(),
                            a.get(i).getWidth(), a.get(i).getHeight());
                    result.attach(a.get(i), 0, current_level.getTop());
                    count++;
                    placed = true;
                }
            }
            if (temp!=null) levels.add(temp);
        }
        result.setHeight(levels.get(levels.size()-1).getTop());
    }

    public Plate execute(ArrayList<Detail> a, int plate_width) {
        Collections.sort(a, new Detail.CompareByWidth());
        int firstSplit = -1, secondSplit = -1;
        int i = 0;
        while (i < a.size() && firstSplit == -1) {
            if (a.get(i).getWidth() <= 2*plate_width/3)
                firstSplit = i;
            else i++;
        }
        while (i < a.size() && secondSplit == -1) {
            if (a.get(i).getWidth() <= plate_width/2)
                secondSplit = i;
            else i++;
        }
        if (firstSplit == -1) {
            firstSplit = a.size() - 1;
            secondSplit = a.size() - 1;
        }

        ArrayList<Level> levels = new ArrayList<Level>();
        Plate result = new Plate(plate_width);

        if (firstSplit == 0 && secondSplit == 0) {
            FFDH(a, result, levels);
        }
        else if (firstSplit == 0 || firstSplit == secondSplit) {
            FFDH(a.subList(0, secondSplit), result, levels);
            FFDH(a.subList(secondSplit, a.size()), result, levels);
        }
        else {
            FFDH(a.subList(0, firstSplit), result, levels);
            FFDH(a.subList(firstSplit, secondSplit), result, levels);
            FFDH(a.subList(secondSplit, a.size()), result, levels);
        }
        return result;
    }
    public String getName() {
        return "SF";
    }
}
