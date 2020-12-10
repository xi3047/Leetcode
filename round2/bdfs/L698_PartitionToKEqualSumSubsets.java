package round2.bdfs;

import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author Xi Zhang
 * @date 12/8/2020 2:52 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/partition-to-k-equal-sum-subsets/
 * @description
 * Given an array of integers nums and a positive integer k, find whether it's possible to divide this array into k non-empty subsets whose sums are all equal.

 * Example 1:
 *
 * Input: nums = [4, 3, 2, 3, 5, 2, 1], k = 4
 * Output: True
 * Explanation: It's possible to divide it into 4 subsets (5), (1, 4), (2,3), (2,3) with equal sums.
 *
 */
public class L698_PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        sum = IntStream.of(nums).sum();
        if (k <= 0 || sum % k != 0) return false;
        boolean[] visited = new boolean[nums.length];
        return canPartition(nums, visited, 0, k, 0,  sum/k);
    }

    private boolean canPartition(int[] nums, boolean[] visited, int start_index, int k, int cur_sum, int target) {
        // if there is only one bucket left
        if (k == 1) return true;

        if (cur_sum > target) {
            return false;
        }
        // if the current bucket is the target sum, we go find the next bucket
        if (cur_sum == target) {
            return canPartition(nums, visited, 0, k - 1, 0, target);
        }

        // for every possible unused number
        for (int i = start_index; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                // explore
                if (canPartition(nums, visited, i + 1, k, cur_sum + nums[i], target)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }

    @Test
    public void test() {
        System.out.println(canPartitionKSubsets(new int[]{4,3,2,3,5,2,1}, 4));
    }


}
