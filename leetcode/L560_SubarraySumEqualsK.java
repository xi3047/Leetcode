package leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
    @author: Xi Zhang
    @date:   1/27/19
    @time:   11:16 PM
 */
public class L560_SubarraySumEqualsK {

    // use a hashmap to store the frequency of prefix sum
    public int subarraySum(int[] nums, int k) {
        int sum = 0, result = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preSum.containsKey(sum - k)) {
                result += preSum.get(sum - k);
            }
            preSum.put(sum, preSum.getOrDefault(sum,0) + 1);
        }

        return result;
    }

    // Solution 1: using prefix sum and loop twice
    public int subarray(int[] nums, int k) {
       int count = 0;
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == k) count++;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] - nums[i] == k) {
                    count++;
                }
            }
        }
        return count;
    }

    @Test
    public void test() {
        int[] nums = {1 , 1, 1};
        System.out.println(subarray(nums , 2));
    }

}
