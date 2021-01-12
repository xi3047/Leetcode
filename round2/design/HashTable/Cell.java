package round2.design.HashTable;

/**
 * @author Xi Zhang
 * @date 12/19/2020 7:30 PM
 * @topic round2.design.HashTable
 * @link
 * @description
 */
public class Cell<K, V> {
    private K key;
    private V value;

    public Cell(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() {
        return this.key == null ? 0 : this.key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cell<?, ?>) {
            Cell<K,V> that = (Cell<K, V>) o;
            if (key == null) {
                return that.key == null;
            } else {
                return key.equals(that.key);
            }
        } else {
            return false;
        }
    }

    public boolean keyEquals(K key) {
        if (this.key == null) {
            return key == null;
        } else {
            return this.key.equals(key);
        }
    }
    public K getKey() {
        return this.key;
    }
    public V getValue() {
        return this.value;
    }
}
