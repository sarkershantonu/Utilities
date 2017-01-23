package org.automation.testing;
import java.util.List;

public class Matching {
 public static <E1,E2>int compare(List<E1> original, List<E2> expected){
        int i = -1;
       
        if(expected.size()==original.size())
        {
            i++;
            for (int j =0;j<original.size();j++) {
                System.out.println("Checking " + original.get(j).toString() + " with " + expected.get(j).toString());
                if (original.get(j).equals(expected.get(j))) {
                    i++;
                }
            }
            if(i!=original.size()) {//all items should be matched.
                i = -1;
            }
        }
        return i;
    }
}
