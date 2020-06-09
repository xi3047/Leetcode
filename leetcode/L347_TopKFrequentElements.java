package leetcode;

import org.junit.Test;

import java.util.*;

public class L347_TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {

        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);

        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for (int num : map.keySet()) {
            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        for (int i : minHeap) {
            res.add(i);
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(topKFrequent(nums, 2));
    }
}
