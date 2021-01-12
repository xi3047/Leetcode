package round2.design.HashTable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author Xi Zhang
 * @date 12/19/2020 7:20 PM
 * @topic round2.design
 * @link
 * @description
 * Design of HashMap from scratch
 */
public class HashTable<K,V> {
    private static final double LOAD_FACTOR = 0.75;
    private int MAX_SIZE = 256;
    private List<Cell<K, V>>[] items;
    private int size;

    public HashTable() {
        this.items = (List<Cell<K, V>> []) new LinkedList[MAX_SIZE];
        this.size = 0;
    }

    public int hashCodeOfKey(K key) {
        return (key == null ? 0 : Math.abs(key.hashCode() % MAX_SIZE));
    }

    public void put(K key, V value) {
        Cell<K, V> newCell = new Cell<>(key, value);
        int index = hashCodeOfKey(key);
        if (items[index] == null) {
            items[index] = new LinkedList<>();
        }
        List<Cell<K,V>> slot = items[index];
        for (Cell<K,V> cell : slot) {
            if (newCell.equals(cell)) {
                slot.remove(cell);
                this.size--;
                break;
            }
        }
        this.size++;
        slot.add(newCell);

        if (this.size > MAX_SIZE * LOAD_FACTOR) {
            rehashing();
        }
    }

    public V get(K key) {
        int index = hashCodeOfKey(key);
        if (items[index] == null) return null;

        List<Cell<K, V>> slot = items[index];
        for (Cell<K, V> cell : slot) {
            if (cell.keyEquals(key)) {
                return cell.getValue();
            }
        }
        return null;
    }

    private void rehashing() {
        MAX_SIZE *= 2;
        List<Cell<K, V>> [] newItems = (List<Cell<K, V>> []) new LinkedList[MAX_SIZE];

        for (List<Cell<K, V>> slot : items) {
            if (slot != null) {
                for (Cell<K, V> cell : slot) {
                    int index = hashCodeOfKey(cell.getKey());
                    if (newItems[index] == null) {
                        newItems[index] = new LinkedList<>();
                    }
                    newItems[index].add(cell);
                }
            }
        }
        this.items = newItems;
    }


}
