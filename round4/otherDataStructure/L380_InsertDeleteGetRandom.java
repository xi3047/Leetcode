package round4.otherDataStructure;

import java.util.*;

public class L380_InsertDeleteGetRandom {
    class RandomizedSet {
        Map<Integer, Integer> map;
        List<Integer> list;
        Random rand;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            rand = new Random();
        }

        // true if not exist, and inserted
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }

            int index = map.get(val);
            int last = list.get(list.size() - 1);

            list.set(index, last);
            map.put(last, index);
            list.remove(list.size() - 1);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(rand.nextInt(list.size()));
        }
    }
}
