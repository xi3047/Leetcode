package leetcode;

import org.junit.Test;

public class L75_SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int left = 0;
        int right = nums.length - 1;
        int div = 0;

        while (div <= right) {
            if (nums[div] == 0) {
                swap(nums, left++, div++);
            } else if (nums[div] == 1) {
                div++;
            } else {
                swap(nums, div, right--);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test() {
        int[] nums = {2, 0, 2, 1, 1, 0};
        sortColors(nums);
        for (int n : nums) {
            System.out.print(n);
        }

    }


}
