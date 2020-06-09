package OOD.MyMap;

public class MyMap<K, V> {


    private Bucket<K, V>[] buckets;
    private int capacity = 256; // to achieve average O(1), set capacity to n/0.75
    int size = 0;

    public MyMap () {
        this.buckets = new Bucket[capacity];

    }

    public MyMap(int capacity) {
        this.buckets = new Bucket[capacity];
    }

    private int hashFunction(K key) {
        return key == null? 0 : key.hashCode() % capacity;
    }

    public V get(K key) {
        int idx = hashFunction(key);
        if (buckets[idx] == null) return null;
        return buckets[idx].get(key);
    }

    public void put(K key, V val) {
        int idx = hashFunction(key);
        if (buckets[idx] == null) buckets[idx] = new Bucket<>();
        boolean isUpdate = buckets[idx].put(key, val);
        if (!isUpdate) size++;
        if (size > capacity * (0.75)) {
            rehash();
        }
    }

    public boolean remove(K key) {
        int idx = hashFunction(key);
        if (buckets[idx] == null) return false;
        boolean found = buckets[idx].remove(key);
        if (found) size--;
        return found;
    }

    private void rehash() {
        capacity *= 2;
        Bucket<K,V>[] newBuckets = new Bucket[capacity];
        for (Bucket b : buckets) {
            for (Object newC: b.getCells()) {
                Cell<K,V> c = (Cell<K,V>) newC;
                int idx = hashFunction(c.getKey());
                if (newBuckets[idx] == null) {
                    newBuckets[idx] = new Bucket();
                }
                newBuckets[idx].put(c.getKey(), c.getVal());
            }
        }

        buckets = newBuckets;
    }

    public static void main(String[] args) {
        MyMap<String, Integer> table =
                new MyMap<String, Integer>();

        String[] keys = {"abc", "xyz", "abc", "vv", "x"};
        Integer[] values = {1,10,2,5,null};

        int len  = keys.length;

        System.out.println(table.get(""));
        System.out.println(table.get(null));

        for (int i = 0; i < len; i++) {
            table.put(keys[i], values[i]);
            Integer val = table.get(keys[i]);
            System.out.println(val);
        }


        System.out.println(table.get(""));

        for (int i = 0; i < len; i++) {
            System.out.println(table.get(keys[i]));
        }

    }
}
