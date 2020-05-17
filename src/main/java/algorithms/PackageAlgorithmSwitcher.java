package algorithms;

import java.util.ArrayList;
import structures.Detail;
import structures.Plate;

public class PackageAlgorithmSwitcher {

    private PackageAlgorithm packageAlgorithm;

    public PackageAlgorithmSwitcher(PackageAlgorithm packageAlgorithm){
        this.packageAlgorithm = packageAlgorithm;
    }

    public void setPackageAlgorithm(PackageAlgorithm packageAlgorithm) {
        this.packageAlgorithm = packageAlgorithm;
    }
    public Plate execute(ArrayList<Detail> detailArrayList){
        return this.packageAlgorithm.execute(detailArrayList);
    }
}
