package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/8/2020 8:41 PM
 * @topic round2.binarySearch
 * @link https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
public class L153_FindMinimumInRotatedSortedArray {
    /**
     * 这里可以使用模板一和模板二，因为一直是和结尾的数字比较，所以一旦在查找最小值时右边界越过了，
     * 通过和结尾数字比较也能把左边界拉回来 e.g.
     * ==> 4 5 6 0 1 2 3
     * ==> l     m     r
     * ==> l m r               这里的r已经越界了，但是通过mid 6与结尾3的，我们知道pivot在中间，所以向右搜寻
     * ==>       l m   r
     *
     * 154题不能使用模板一因为我们是要用right查重和要与right做比较，一旦越界,我们 e.g.
     * ==> 4 5 6 0 1 2 3
     * ==> l     m     r
     * ==> l m r
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[nums.length - 1]) left = mid + 1;
            else right = mid;
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int [] arr = new int[]{4,5,6,0,1,2,3};
        L153_FindMinimumInRotatedSortedArray solution = new L153_FindMinimumInRotatedSortedArray();
        System.out.println(solution.findMin(arr));
    }
}
