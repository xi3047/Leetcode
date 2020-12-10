package round2.heap_stack;


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
    /*
    Solution 1: using a minHeap and hashmap
    T(n): O(nlogk)
     */
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null) return null;
        Map<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> map.get(a) - map.get(b));
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : map.keySet()) {
            minHeap.offer(num);
            while (minHeap.size() > k) minHeap.poll();
        }
        int [] arr = new int[minHeap.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = minHeap.poll();
        }
        return arr;
    }


     /*
     Solution 2: using Bucket
     O(nums.length) time because we iterate nums to create the frequency map and then do a bucket sort.
     O(nums.length) space because of the array we use for bucket sort, also all numbers could be distinct and
     in this case the HashMap would have one entry for each element.
      */
    public int[]  topKFrequentBucket(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) return new int[0];
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int currNum : nums) freqMap.put(currNum, freqMap.getOrDefault(currNum, 0) + 1);

        List<Integer>[] buckets = new ArrayList[nums.length + 1];   // Number of occurrences of all elements must be in [0, nums.length].
        for (int key : freqMap.keySet()) {
            if (buckets[freqMap.get(key)] == null) buckets[freqMap.get(key)] = new ArrayList<>();
            buckets[freqMap.get(key)].add(key);
        }
        // In case we want to return less than k elements, k could be greater than the number of distinct elements in nums.
        int[] result = new int[Math.min(freqMap.size(), k)];
        int resIdx = 0;
        for (int i = buckets.length - 1; i >= 0; --i) {
            if (buckets[i] == null) continue;                       // Because we only initialized buckets that we inserted elements into, empty buckets are null values.
            for (int currNum : buckets[i]) {
                result[resIdx++] = currNum;
                if (resIdx == result.length) return result;
            }
        }
        return result;
    }

    /*
    Solution 3: using TreeMap
     */

    @Test
    public void test() {
        Arrays.stream(topKFrequent(new int[] {1,1,1,2,2,3}, 2)).forEach(i -> System.out.println(i));
    }
}
