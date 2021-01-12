package round2.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Xi Zhang
 * @date 1/10/21 12:20 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/tweet-counts-per-frequency/
 * @description
 */
public class L1348_TweetCountsPerFrequency {
    Map<String, TreeMap<Integer, Integer>> map;
    public L1348_TweetCountsPerFrequency() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {
        map.putIfAbsent(tweetName, new TreeMap<>());
        map.get(tweetName).put(time, map.get(tweetName).getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        if (!map.containsKey(tweetName)) return null;
        int interval, size;
        if (freq.equals("minute")) {
            interval = 60;
        } else if (freq.equals("hour")) {
            interval = 3600;
        } else {
            interval = 86400;
        }
        size = ((endTime - startTime) / interval) + 1;
        int[] bucket = new int[size];
        TreeMap<Integer, Integer> tweetMap = map.get(tweetName);

        for (Map.Entry<Integer, Integer> entry : tweetMap.subMap(startTime, endTime + 1).entrySet()) {
            int index = (entry.getKey() - startTime) / interval;
            bucket[index] += entry.getValue();
        }
        List<Integer> res = new LinkedList<>();
        for (int num : bucket) {
            res.add(num);
        }
        return res;
    }
}
