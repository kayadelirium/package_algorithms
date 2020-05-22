import java.io.IOException;
import java.util.ArrayList;

import algorithms.*;
import structures.*;
import io.*;

public class Main {
    public static void main(String[] args) {
        try {
            final int plate_width = 500;
            Statistics.setPath("tests_input");
            Statistics.setNumberOfTests(100);
            Statistics.setPlate_width(plate_width);

            Statistics.generateTests();
            Statistics.executeTests("tests_output");
/*
            Input.setPath("test2.xlsx");
            ArrayList<Detail> detailSet = null;
            detailSet = Input.makeList("details", 1);

            if (detailSet != null) {

                for (Detail detail : detailSet)
                    if (detail.getWidth() > plate_width) {
                        new Drawing();
                        return;
                    }

                PackageAlgorithmSwitcher switcher = new PackageAlgorithmSwitcher(new Join());
                Plate first_and_last = switcher.execute(detailSet, plate_width);

                new Drawing(first_and_last);

                Output.setPath("output.xlsx");
                Output.createExcel(first_and_last, switcher.getName());
            }
*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}