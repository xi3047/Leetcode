package round1;
/*
    @author: Xi Zhang
    @date:   2019-02-17
    @time:   21:13
    @Description : LeetCode 381. Insert Delete GetRandom O(1) - Duplicates allowed

    Idea: With duplicate allowed means we can put element with same value into the collection, so in this case,
    for example, we have 8 5 5 7 , and to insert another 5, 5 will have list of index(1,2,4). to remove 5, we get the last index of the list
    and swap it with the last element of the list, and set cur index to last element then remove the last element. Now to get random, suppose
    we have 8 5 5 5 7, getting one 5 should have a probability of 1/5,
 */
import java.util.*;
class L381_RandomizedCollection {
    class Entry {
        public int value;
        public int index;
        public Entry(int val, int idx) {
            value = val;
            index = idx;
        }
    }

    private Map<Integer, List<Integer>> m;
    private List<Entry> vals;
    private Random rand;

    /** Initialize your data structure here. */
    public L381_RandomizedCollection() {
        m = new HashMap<>();
        vals = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    //
    public boolean insert(int val) {
        List<Integer> l = m.getOrDefault(val, new ArrayList<Integer>());
        l.add(vals.size());
        m.put(val, l);
        vals.add(new Entry(val, l.size() - 1));
        return l.size() == 1;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!m.containsKey(val)) return false;
        List<Integer> l = m.get(val);
        int index_to_evict = l.get(l.size() - 1);
        Entry last_entry = vals.get(vals.size() - 1);

        // Update index
        m.get(last_entry.value).set(last_entry.index, index_to_evict);

        // Swap vals
        vals.set(index_to_evict, last_entry);

        // Cleanup
        vals.remove(vals.size() - 1);
        l.remove(l.size() - 1);
        if (l.size() == 0) m.remove(val);

        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return vals.get(rand.nextInt(vals.size())).value;
    }
}