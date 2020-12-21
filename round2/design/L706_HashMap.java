package round2.design;

import java.util.Arrays;

/**
 * @author Xi Zhang
 * @date 12/19/2020 7:13 PM
 * @topic round2.design
 * @link
 * @description
 */
public class L706_HashMap {
    /** Initialize your data structure here. */
    int[] map;
    public L706_HashMap() {
        map = new int[1000000];
        Arrays.fill(map, -1);
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        map[key] = value;
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        return map[key];
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        map[key] = -1;
    }
}
