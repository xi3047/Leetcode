package round2.design.HashTable;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/19/2020 7:20 PM
 * @topic round2.design
 * @link
 * @description
 * Design of HashMap from scratch
 */
public class HashTable<K,V> {
    private static final double lOAD_FACTOR = 0.75;
    private int MAX_SIZE = 256;
    private List<Cell<K, V>>[] items;
    private int size;

    public HashTable() {
        this.items = (List<Cell<K, V>> []) new LinkedList[MAX_SIZE];
        this.size = 0;
    }




}
