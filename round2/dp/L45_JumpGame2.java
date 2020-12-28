package round2.dp;

/**
 * @author Xi Zhang
 * @date 12/27/2020 11:12 PM
 * @topic round2.dp
 * @link https://leetcode.com/problems/jump-game-ii/
 * @description
 * iven an array of non-negative integers nums, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Your goal is to reach the last index in the minimum number of jumps.
 *
 * You can assume that you can always reach the last index.
 *
 * Example 1:
 *
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * Example 2:
 *
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 */
public class L45_JumpGame2 {
    public int jump(int[] nums) {
        int end = 0;
        int max = 0;
        int step = 0;
        for (int i = 0; i < nums.length -1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == end) {
                end = max;
                step++;
            }
        }
        return step;
    }
}
