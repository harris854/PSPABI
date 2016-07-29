/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Analysis;

import java.util.Comparator;
/**
 * @author Jwala
 */
public class SortByItem  implements Comparator<DataCapsule> {
    
    @Override
    public int compare(DataCapsule o1, DataCapsule o2) {
        if (o1.getItem() >o2.getItem()) {
            return 1;
        } else {
            return 0;
        }
    }
}
    

