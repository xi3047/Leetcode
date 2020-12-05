package round1;

public class L238_ProductofArrayExceptItself {
    /*
        @author: Xi Zhang
        @date:   2019-02-15
        @time:   16:26

        Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

        Example:

        Input:  [1,2,3,4]
        Output: [24,12,8,6]
        Note: Please solve it without division and in O(n).

        Follow up:
        Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int[] leftP = new int[nums.length];
        int[] rightP = new int[nums.length];
        leftP[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            leftP[i] = leftP[i-1] * nums[i];
        }
        rightP[nums.length-1] = nums[nums.length-1];
        for (int i = nums.length - 2; i >= 0; i--) {
            rightP[i] = nums[i] * rightP[i + 1];
        }

        int[] output = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                output[i] = rightP[i+1];
                continue;
            }
            if (i == nums.length-1) {
                output[i] = leftP[i-1];
                continue;
            }
            output[i] = leftP[i-1] * rightP[i+1];
        }
        return output;
    }
}
