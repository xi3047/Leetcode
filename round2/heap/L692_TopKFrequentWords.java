package round2.heap;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/29/2020 3:32 PM
 * @topic round2.heap
 * @link https://leetcode.com/problems/top-k-frequent-words/
 * @description
 */
public class L692_TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new LinkedList<>();
        if (words == null || words.length == 0) return res;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> maxHeap = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (map.get(o1) != map.get(o2)) {
                    return map.get(o2) - map.get(o1);
                } else {
                    return o2.compareTo(o1);
                }
            }
        });
        for (String word : map.keySet()) {
            maxHeap.offer(word);
        }
        while (!maxHeap.isEmpty()) res.add(maxHeap.poll());
//        while ( k --> 0) {
//            res.add(maxHeap.poll());
//        }
        return res;
    }

    @Test
    public void test() {
        String[] words = {"one", "two", "two", "twa", "twa", "three", "three", "three" , "four" ,"four","four","four"};
        System.out.println(topKFrequent(words,2));
        System.out.println("a".compareTo("c"));
    }
}
