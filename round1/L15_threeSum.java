package round1;

import java.util.*;

public class L15_threeSum {
    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length < 3) return res;
        for (int i = 0; i < nums.length -2; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            int left = i + 1;
            int right = nums.length -1;
            int complement = 0 - nums[i];
            while (left < right) {
                if (nums[left] + nums[right] == complement) {
                    List<Integer> list = Arrays.asList(nums[i], nums[left], nums[right]);
                    res.add(list);
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                } else if (nums[left] + nums[right] < complement) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;

    }


    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, 4};
        List<List<Integer>> res = threeSum(arr);
        System.out.print(res);

    }
}
