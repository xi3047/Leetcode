package round4.otherDataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class L981_TimeBasedKeyValueStore {
    class TimeMap {
        Map<String, TreeMap<Integer, String>> map;
        public TimeMap() {
            map = new HashMap<>();
        }

        public void set(String key, String value, int timestamp) {
            map.computeIfAbsent(key, x -> new TreeMap<>()).put(timestamp, value);
        }

        public String get(String key, int timestamp) {
            TreeMap<Integer, String> treeMap = map.get(key);
            if (treeMap == null) return "";
            Integer k = treeMap.floorKey(timestamp);
            if (k == null) return "";
            return treeMap.get(k);
        }
    }

}
