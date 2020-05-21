package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import structures.Detail;
import structures.Level;
import structures.Plate;


public class SF implements PackageAlgorithm {
    public void FFDH(List<Detail> a, Plate result, ArrayList<Level> levels){
        Collections.sort(a, new Detail.CompareByHeight());
        int n = a.size();
        Level temp = null;
        for (int i = 1; i < n; i++){
            boolean placed = false;
            for (Level current_level: levels){
                if (current_level.isSufficient(a.get(i).getWidth(), result.getWidth()) && !placed && (a.get(i).getHeight() <= current_level.getTop() - current_level.getBottom())){
                    result.attach(a.get(i), current_level.getWidth(), current_level.getBottom());
                    current_level.increaseWidth(a.get(i).getWidth());
                    temp = null;
                    placed = true;
                }
                else if (current_level == levels.get(levels.size() - 1) && !placed){
                    temp = new Level(current_level.getTop(),
                            current_level.getTop() + a.get(i).getHeight(),
                            a.get(i).getWidth(), a.get(i).getHeight());
                    result.attach(a.get(i), 0, current_level.getTop());
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
            if (a.get(i).getWidth() <= (2*plate_width/3))
                firstSplit = i;
            else i++;
        }
        while (i < a.size() && secondSplit == -1) {
            if (a.get(i).getWidth() <= (plate_width/2))
                secondSplit = i;
            else i++;
        }
        if (firstSplit==-1) {
            firstSplit = a.size() - 1;
            secondSplit = a.size() - 1;
        }
        // System.out.println(firstSplit +" "+secondSplit);
        ArrayList<Level> levels = new ArrayList<Level>();
        Plate result = new Plate(plate_width);
        Level temp = new Level(0, a.get(0).getHeight(), a.get(0).getWidth(), a.get(0).getHeight());
        result.attach(a.get(0), 0, 0);
        levels.add(temp);

        if (firstSplit==0 || firstSplit == 1) {
            if (secondSplit <= 1) FFDH(a.subList(1, a.size()), result, levels);
            else {
                FFDH(a.subList(1, secondSplit), result, levels);
                FFDH(a.subList(secondSplit, a.size()), result, levels);
            }
        }
        else {
            if (firstSplit == secondSplit) {
                FFDH(a.subList(1, firstSplit), result, levels);
                FFDH(a.subList(firstSplit, a.size()), result, levels);
            } else {
                FFDH(a.subList(1, firstSplit), result, levels);
                FFDH(a.subList(firstSplit, secondSplit), result, levels);
                FFDH(a.subList(secondSplit, a.size()), result, levels);
            }
        }

        // sublist херово работает когда индексы начала и конца совпадают( <3
        // остальное ок (нет)
        // пойду мыть тарелку
       // a.subList(firstSplit,secondSplit);
        //СЕЙЧАС Я ВЫПЬЮ КОФЕ СЪЕМ СВОЙ ДЕСЕРТ ПОКУРЮ И ВЕРНУСЬ С КОНЦАМИ ПРОСТИТЕ
        // <3
        return result;
    }
}
