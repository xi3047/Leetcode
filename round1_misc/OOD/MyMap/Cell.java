package round1_misc.OOD.MyMap;

public class Cell<K, V> {
    private K key;
    private V val;

    public Cell (K key, V val) {
        this.key= key;
        this.val = val;
    }


    public K getKey() {
        return key;
    }

    public V getVal() {
        return val;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setVal(V val) {
        this.val = val;
    }

    public boolean KeyEquals(K thatKey) {
        if (thatKey == null) {
            return this.key == null;
        }
        return thatKey.equals(this.key);
    }
}
