package round1_misc.OOD.MyTable;


public class MyTable<K, V> {
    private Entry<K, V>[] buckets;
    private static final int INITIAL_CAPACITY = 256; // 16
    private int capacity;
    private int size = 0;
    public MyTable() {
        this(INITIAL_CAPACITY);
        this.capacity = INITIAL_CAPACITY;
        this.buckets = new Entry[INITIAL_CAPACITY];
    }
    public MyTable(int capacity) {
        this.buckets = new Entry[capacity];
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
    }

    public int getSize() {
        return size;
    }

    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value, null);
        int idx = Math.abs(key.hashCode() % capacity);
        Entry<K, V> existing = buckets[idx];
        if (existing == null) {
            buckets[idx] = entry;
            size++;
        } else {
            // compare the keys see if key already exists
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }
            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
                size++;
            }
        }
    }

    public V get(K key) {
        int idx = Math.abs(key.hashCode() % capacity);
        Entry<K, V> bucket = buckets[idx];
        while (bucket != null) {
            if (bucket.key.equals(key)) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

//    @Test
//    public void testMyTable() {
//        MyTable<String, String> myTable = new MyTable<>(4);
//        myTable.put("USA", "Washington DC");
//        myTable.put("China", "Beijing");
//        myTable.put("India", "New Delhi");
//        myTable.put("Australia", "Sydney");
//        assertNotNull(myTable);
//        assertEquals(4, myTable.getSize());
//        assertEquals("Kathmandu", myTable.get("Nepal"));
//        assertEquals("Sydney", myTable.get("Australia"));
//    }

    public static void main(String[] args) {
        MyTable<String, String> myTable = new MyTable<>();
        myTable.put("USA", "Washington DC");

        myTable.put("China", "Beijing");
        System.out.println(myTable.get("China"));
        myTable.put("India", "New Delhi");
        myTable.put("Australia", "Sydney");
        myTable.put("China", "Shanghai");
        System.out.println(myTable.get("China"));
//        System.out.println(myTable.get("India"));
//        System.out.println(myTable.get("USA"));
//        System.out.println(myTable.get("Australia"));
//        System.out.println(myTable.get("England"));
    }

}
