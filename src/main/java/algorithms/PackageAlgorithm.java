package algorithms;

import java.util.ArrayList;
import structures.Detail;
import structures.Plate;


public interface PackageAlgorithm {

    Plate execute(ArrayList<Detail> a, int plate_width);

    String getName();
}
