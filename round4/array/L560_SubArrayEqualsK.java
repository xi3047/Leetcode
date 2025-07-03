package round4.array;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class L560_SubArrayEqualsK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        Map<Integer, Integer> sumMap = new HashMap<>();
        sumMap.put(0, 1);
        int count = 0;
        int currentSum = 0;
        for (int num : nums) {
            currentSum += num;
            if (sumMap.containsKey(currentSum - k)) {
                count += sumMap.get(currentSum - k);
            }
            sumMap.put(currentSum, sumMap.getOrDefault(currentSum, 0 ) + 1);
        }
        return count;
    }

    /**
     * Variant 1: return true if there is one subarray whose sum equals to k
     */
    public boolean subArrayFound(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int currentSum = 0;
        for (int num : nums) {
            currentSum += num;
            int complement = currentSum - k;
            if (set.contains(complement)) {
                return true;
            }
            set.add(currentSum);
        }
        return false;
    }
    /**
     * Variant 2 : only positive integers
     */
    public boolean subArray(int[] nums, int k) {
        int sum = 0;
        int left = 0;
        int right = 0;
        while (right < nums.length) {
            sum += nums[right];

            while (sum > k) {
                sum -= nums[left];
                left++;
            }
            if (sum == k) return true;
            right++;
        }
        return false;
    }
}
