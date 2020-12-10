package round2.design;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/4/2020 10:00 PM
 * @topic round2.design
 * @link
 * @description
 */
public class L380_InsertDeleteGetRandom {
    /** Initialize your data structure here. */
    Map<Integer, Integer> map;
    List<Integer> list;
    public L380_InsertDeleteGetRandom() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        if (list.size() != 1) {
            int lastValue = list.get(list.size() - 1);
            int thisIndex = map.get(val);
            list.set(thisIndex, lastValue);
            map.put(lastValue, thisIndex);
        }
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        Random random = new Random();
        int randomIndex = random.nextInt(list.size());
        return list.get(randomIndex);
    }
}
