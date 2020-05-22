package algorithms;

import java.util.*;

import structures.Detail;
import structures.Plate;

public class Join implements PackageAlgorithm{

    public Plate execute(ArrayList<Detail> a, int plate_width) {

        Collections.sort(a, new Detail.CompareByHeight());
        ArrayList <Detail> paired = new ArrayList<Detail>();
        ArrayList <Detail> unpaired = new ArrayList<Detail>();

       HashMap<Detail,List<Detail>> hash = new HashMap<Detail, List<Detail>>();

        for(int i= 0; i < a.size() - 1; i++) {
            if (a.get(i).getHeight() - a.get(i+1).getHeight() <= 0.05*a.get(i).getHeight()
                    && a.get(i).getWidth() + a.get(i+1).getWidth() <= plate_width) {
                Detail tempDetail = new Detail(a.get(i).getId(), a.get(i).getWidth() + a.get(i + 1).getWidth(), a.get(i).getHeight());
                paired.add(tempDetail);
                ArrayList<Detail> tempList = new ArrayList<Detail>();
                tempList.add(a.get(i));
                tempList.add(a.get(i+1));
                hash.put(tempDetail, tempList);
                i++;
            } else {
                unpaired.add(a.get(i));
                hash.put(a.get(i), a.subList(i,i+1));
            }
        }
        paired.addAll(unpaired);

        FFDH ffdh = new FFDH();
        Plate plate = ffdh.execute(paired, plate_width);
        /*NFDH nfdh = new NFDH();
        Plate plate = nfdh.execute(paired, plate_width);*/

        ArrayList<Detail> newList = new ArrayList<Detail>();
        for(Detail detail: plate.getList()){
            List<Detail> temp = hash.get(detail);
            temp.get(0).setX(detail.getX());
            temp.get(0).setY(detail.getY());
            if (temp.size()==2)
            {
                temp.get(1).setX(detail.getX()+temp.get(0).getWidth());
                temp.get(1).setY(detail.getY());
            }
            newList.addAll(temp);
        }
        plate.setList(newList);
        return plate;
    }
    public String getName() {
        return "Join";
    }
}
