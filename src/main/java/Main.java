import java.io.IOException;
import java.util.ArrayList;

import algorithms.*;
import structures.*;
import io.*;

public class Main {
    public static void main(String[] args) {
        Input.setPath("test2.xlsx");
        ArrayList<Detail> detailSet = null;
        try {
            detailSet = Input.makeList("details", 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (detailSet != null) {
            final int plate_width = 600;
            for (Detail detail : detailSet)
                if (detail.getWidth() > plate_width) {
                    new Drawing();
                    return;
                }

            PackageAlgorithmSwitcher switcher = new PackageAlgorithmSwitcher(new Join());
            Plate first_and_last = switcher.execute(detailSet, plate_width);

            new Drawing(first_and_last);

            Output.setPath("output.xlsx");
            try {
                Output.createExcel(first_and_last, 0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
