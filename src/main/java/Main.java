import algorithms.FFDH;
import algorithms.NFDH;
import algorithms.PackageAlgorithmSwitcher;
import io.Input;
import io.Output;
import structures.Plate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import structures.Detail;

public class Main {
    public static void main(String[] args){
        Input.setPath("test0.xlsx");
        ArrayList<Detail> detailSet = null;
        try {
            detailSet = Input.makeList("details", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*
        for (int i = 0; i < detailSet.size(); i++){
            System.out.println(detailSet.get(i).getHeight());
            System.out.println(detailSet.get(i).getWidth());
            System.out.println(detailSet.get(i).getX());
            System.out.println(detailSet.get(i).getY());
            System.out.println("-----------");
        }
*/



        PackageAlgorithmSwitcher switcher = new PackageAlgorithmSwitcher(new FFDH());
        Plate first_and_last = switcher.execute(detailSet, 500);

        System.out.println(first_and_last);

        //алгоритмы
        Drawing rects = new Drawing(first_and_last.getList());
        Output.setPath("output.xlsx");
        try {
            Output.createExcel(first_and_last,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
