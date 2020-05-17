import io.Input;
import io.Output;
import structures.Plate;

import java.io.IOException;
import java.util.ArrayList;
import structures.Detail;

public class Main {
    public static void main(String[] args){
        Input.setPath("data0.xlsx");
        ArrayList<Detail> detailSet = null;
        try {
            detailSet = Input.makeList("Sklad listov", 1, 9, 10);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < detailSet.size(); i++){
            System.out.println(detailSet.get(i).getHeight());
            System.out.println(detailSet.get(i).getWidth());
            System.out.println(detailSet.get(i).getX());
            System.out.println(detailSet.get(i).getY());
            System.out.println("-----------");
        }

        Plate first_and_last = new Plate(0,0);// длина-ширина
        //алгоритмы

        first_and_last.attach(new Detail(1,2),1,2);
        Output.setPath("data2.xlsx");

        try {
            Output.createExcel(first_and_last,0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawing drawing = new Drawing(first_and_last.getList());
    }

}
