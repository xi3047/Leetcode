package round2.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 11/15/2020 1:15 PM
 * @topic round2.array
 * @link
 */
public class L560_SubarraySumEqualK {
    public int subarraySum(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> sums = new HashMap<>();
        sums.put(0, 1);
        int count = 0;
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            count += sums.getOrDefault(prefixSum - k, 0);
            sums.put(prefixSum, sums.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
