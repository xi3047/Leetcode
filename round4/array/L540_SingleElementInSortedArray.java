package round4.array;

public class L540_SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {


        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) {
                mid -= 1;
            }
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else {
                right = mid;
            }

        }
        return nums[left];

    }
}
