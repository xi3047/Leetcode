package round1;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   15:17
 */
public class L268_MissingNumber {

    // Solution 1: using XOR on all numbers and the indices
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for(int i = 0; i < nums.length; i++){
            res ^= i ^ nums[i];
        }
        return res;
    }

    // Solution 2: use math formula, 0 + 1 + 2 + 3 + ... + n = n(n+1) / 2, then subtract the sum of numbers from it
    public int missingNumber2(int[] nums) {
        int len = nums.length;
        int sum = (0 + len) * (len + 1) / 2;
        for(int i = 0; i < len; i++)
            sum -= nums[i];
        return sum;
    }

    // Solution 3: binary search
    public int missingNumber3(int[] nums) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > mid) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    // Solution 4: HashSet, two pass
    public int missNumber4(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        int res = 0;
        for (int i = 0; i <= nums.length; i++) {
            if (!set.contains(i)) {
                res = i;
                break;
            }
        }
        return res;
    }

    @Test
    public void test() {
        int[] nums = {3, 0, 4, 1};
        System.out.println(missNumber4(nums));
    }
}
