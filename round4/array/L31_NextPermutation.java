package round4.array;

public class L31_NextPermutation {
    public void nextPermutation(int[] nums) {
       int n = nums.length;
       int i = n - 2;
        // Step 1: Find the first decreasing element from the end
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        // Step 2: If found, go from end again and
        // find just larger element to swap
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
       // reverse rest of element in increasing order
       reverse(nums, i + 1, n -1 );
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    public void prevPermutation(int[] nums) {

    }
}
