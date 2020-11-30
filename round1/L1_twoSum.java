package round1;

import org.junit.Test;

import java.util.*;

public class L1_twoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No such two elements.");
    }

    @Test
    public void test() {
        int[] nums = {2, 3, 5, 2, 7};
        int target = 4;
        int[] res = twoSum(nums, target);
        for (int n : res) {
            System.out.println(n);
        }
        // adding something
    }




}
