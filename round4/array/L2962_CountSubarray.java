package round4.array;

public class L2962_CountSubarray {
    public long countSubarrays(int[] nums, int k) {
        int max = 0;
        for (int num : nums) {
            if (num > max) max = num;
        }
        int res = 0;
        int count = 0;
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] == max) {
                count++;
            }

            while (count >= k) {
                if (nums[left] == max) count--;
                left++;
            }
            res += left;
        }
        return res;

    }
}
