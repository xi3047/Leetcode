package leetcode;

import org.junit.Test;

import java.util.*;

public class L692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> minHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (map.get(o1) != map.get(o2)) {
                    return map.get(o1) - map.get(o2);
                } else {
                    return o2.compareTo(o1);
                }
            }
        });

        for (String word : map.keySet()) {
            minHeap.offer(word);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        while (!minHeap.isEmpty()) {
            res.add(0, minHeap.poll());
        }

        return res;
    }

    @Test
    public void test(){
        String[] words = {"i", "love", "leetcode", "i", "love", "coding"};
        //System.out.println(topKFrequent(words,2));
        System.out.println("i".compareTo("love"));
    }
}
