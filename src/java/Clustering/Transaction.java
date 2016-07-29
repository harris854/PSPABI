/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Clustering;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Jwala
 */
public class Transaction {
    private int tid;
    ArrayList<Integer> itemlist = new ArrayList<Integer>();
    Cluster cluster = new Cluster();

    Transaction() {
    }

    void AssignCluster(Cluster c) {
        cluster = c;
    }

    void RemoveCluster() {
        cluster = null;
    }

    Transaction(int tid) {
        this.tid = tid;
    }

    void SetList(int item) {
        itemlist.add(item);
    }

    int GetTid() {
        return tid;
    }

    void SortList() {
        Collections.sort(itemlist);
    }
}
