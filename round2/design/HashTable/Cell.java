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
        return false;
    }

    public boolean keyEquals(K key) {
        if (this.key == null) {
            return key == null;
        } else {
            return this.key.equals(key);
        }
    }
}
