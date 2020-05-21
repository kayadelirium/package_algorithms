import java.io.IOException;
import java.util.ArrayList;

import algorithms.*;
import structures.*;
import io.*;

public class Main {
    public static void main(String[] args){
        Input.setPath("test0.xlsx");
        ArrayList<Detail> detailSet = null;
        try {
            detailSet = Input.makeList("details", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*for (int i = 0; i < detailSet.size(); i++){
            System.out.println(detailSet.get(i).getHeight());
            System.out.println(detailSet.get(i).getWidth());
            System.out.println(detailSet.get(i).getX());
            System.out.println(detailSet.get(i).getY());
            System.out.println("-----------");
        }*/



        //Plate first_and_last = new Plate(1000,100000000);// длина-ширина

        PackageAlgorithmSwitcher switcher = new PackageAlgorithmSwitcher(new FCNR());
        Plate first_and_last = switcher.execute(detailSet, 500);

        //алгоритмы

        /*first_and_last.attach(new Detail(100,200),100,20);
        first_and_last.attach(new Detail(200,300),200,40);
        first_and_last.attach(new Detail(300,400),500,70);*/
        //System.out.println(first_and_last.getWidth());
        //System.out.println(first_and_last.getHeight());
        Drawing rects = new Drawing(first_and_last);
        Output.setPath("data3.xlsx");
        try {
            Output.createExcel(first_and_last,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
