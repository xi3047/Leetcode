package OOD.MyMap;

import java.util.ArrayList;
import java.util.List;

public class Bucket<K, V>  {
    private List<Cell<K, V>> cells;

    public Bucket () {
        cells = new ArrayList<Cell<K, V>>();
    }

    public List<Cell<K,V>> getCells() {
        return cells;
    }

    public V get(K key) {
        for (Cell<K, V> c : cells) {
            if (c.KeyEquals(key)) {
                return c.getVal();
            }
        }
        return null;
    }

    public boolean put(K key, V val) { // true -- > update, else add new cell
        for (Cell<K, V> c : cells) {
            if (c.KeyEquals(key)) {
                c.setVal(val);
                return true;
            }
        }
        cells.add(new Cell<>(key, val));
        return false;
    }

    public boolean remove(K key) {
        Cell<K, V> swapMe = null;
        for (Cell<K, V> c : cells) {
            if (c.KeyEquals(key)) {
                swapMe = c;
                break;
            }
        }
        if (swapMe == null) {
            return false;
        }
        Cell<K, V> lastCell = cells.get(cells.size() - 1);
        swapMe.setKey(lastCell.getKey());
        swapMe.setVal(lastCell.getVal());
        cells.remove(cells.size() - 1);
        return true;
    }
}
