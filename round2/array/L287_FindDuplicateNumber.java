package round2.array;

/**
 * @author Xi Zhang
 * @date 12/5/2020 1:56 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L287_FindDuplicateNumber {
    /*
    Solution 1: use a bucket to count
    T(n) = O(n)
    S(n) = O(n)
     */
    public int findDuplicate(int[] nums) {
        int[] map = new int[nums.length - 1];
        for (int i = 0; i < nums.length; i++) {
            int curCount = map[nums[i] - 1];
            if (curCount == 1) {
                return nums[i];
            }
            map[nums[i] - 1] += 1;

        }
        return 0;
    }
    /*
    Solution 2: Construct a linked list, and return the node where the cycle begins
    Floyd algorithm, refer to L142
     */
    public int findDuplicate2(int[] nums) {
        int slow = 0, fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while(slow != fast);
        slow = 0;
        while(slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
