package round2.design;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/18/2020 5:40 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/time-based-key-value-store/
 * @description
 *
 * Create a timebased key-value store class TimeMap, that supports two operations.
 *
 * 1. set(string key, string value, int timestamp)
 *
 * Stores the key and value, along with the given timestamp.
 * 2. get(string key, int timestamp)
 *
 * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
 * If there are multiple such values, it returns the one with the largest timestamp_prev.
 * If there are no values, it returns the empty string ("").
 *
 *
 * Example 1:
 *
 * Input: inputs = ["TimeMap","set","get","get","set","get","get"], inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],["foo",4],["foo",5]]
 * Output: [null,null,"bar","bar",null,"bar2","bar2"]
 * Explanation:
 * TimeMap kv;
 * kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1
 * kv.get("foo", 1);  // output "bar"
 * kv.get("foo", 3); // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 ie "bar"
 * kv.set("foo", "bar2", 4);
 * kv.get("foo", 4); // output "bar2"
 * kv.get("foo", 5); //output "bar2"
 */
/**
 * Solution 1: HashMap + Binary Tree
 **/
class TimeMap {
    class Data{
        int time;
        String value;

        public Data(int time, String value) {
            this.time = time;
            this.value = value;
        }
    }

    Map<String, List<Data>> map;

    public TimeMap() {
        this.map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new ArrayList<>());
        map.get(key).add(new Data(timestamp, value));
    }

    public String get(String key, int timestamp) {

        int foundTime = search(map.get(key), timestamp);
        if (foundTime == -1) return "";
        return map.get(key).get(foundTime).value;
    }

    private int search(List<Data> list, int inputTime) {

        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (inputTime < list.get(mid).time) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }
}

/**
 *
 *
 * Solution 2: TreeMap
 */
class TimeMapTreeMap {
    Map<String, TreeMap<Integer, String>> M;
    /** Initialize your data structure here. */
    public TimeMapTreeMap() {
        M = new HashMap();
    }

    public void set(String key, String value, int timestamp) {
        if (!M.containsKey(key))
            M.put(key, new TreeMap());

        M.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!M.containsKey(key)) return "";
        TreeMap<Integer, String> tree = M.get(key);
        Integer t = tree.floorKey(timestamp);
        return t != null ? tree.get(t) : "";
    }

    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("foo", "bar", 3);
        System.out.println(timeMap.get("foo", 1));
        System.out.println(timeMap.get("foo", 3));
        timeMap.set("foo", "bar2", 6);
        System.out.println(timeMap.get("foo", 4));
        System.out.println(timeMap.get("foo", 7));
    }
}
