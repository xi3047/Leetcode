package round1;
/*
    @author: Xi Zhang
    @date:   2019-02-17
    @time:   20:34
    @Description: LeetCode 380 Insert Delete getRandom O(1)

    Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 */
import java.util.*;

public class L380_RandomizedSet {
    Map<Integer, Integer> map;
    List<Integer> list;
    public L380_RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int index = map.get(val);
        int size = list.size();
        int lastElement = list.get(size - 1);

        list.set(index, lastElement);
        map.put(lastElement, index);

        list.remove(size-1);
        map.remove(val);
        return true;
    }
    public int getRandom() {
        Random random = new Random();
        int randIndex = random.nextInt(list.size());
        return list.get(randIndex);
    }
}
