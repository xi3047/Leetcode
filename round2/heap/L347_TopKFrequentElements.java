package round2.heap;


import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/29/2020 11:11 PM
 * @topic round2.heap
 * @link
 * @description
 */
public class L347_TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            while (minHeap.size() > k) minHeap.poll();
        }
        int [] arr = new int[minHeap.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = minHeap.poll();
        }
        return arr;
    }

    @Test
    public void test() {
        System.out.println(topKFrequent(new int[] {1,1,1,2,2,3}, 2));
    }
}
