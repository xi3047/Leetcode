package round4.array;

public class L1004_MaxConsecutiveOnesIII {
    public int longestOnes(int[] nums, int k) {
        // two pointers
        // increase window
        // shrink window
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == 0) {
                k--;
            }
            while (k < 0) {
                if (nums[left] == 0) {
                    k++;
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    /**
     *   L         R
     *   0   1   2   3  4
     *   W , W , W , W, W
     */
}
