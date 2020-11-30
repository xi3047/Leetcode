package round1;

/*
    @author: Xi Zhang
    @date:   2019-03-05
    @time:   18:10
    Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

    If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

    The replacement must be in-place and use only constant extra memory.

    Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.

    1,2,3 → 1,3,2
    3,2,1 → 1,2,3
    1,1,5 → 1,5,1
 */
public class L31_NextPermutation {
    /** Solution (based on reverse backtracking)
     *  1. find the first non-descending integer from right to left
     *  2. swap the integer with the next greater integer from the right pool
     *  3. reverse the right pool so it is in ascending order
     */
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int i = nums.length - 1;
        while (i >= 0 && nums[i] <= nums[i-1]) {
            i--;
        }
        i--;
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) j--;
            swap(nums, i, j);
        }
        reverse(nums, i + 1, nums.length - 1);
    }
    public void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public void reverse(int[] A, int i, int j) {
        while(i < j) swap(A, i++, j--);
    }
}
