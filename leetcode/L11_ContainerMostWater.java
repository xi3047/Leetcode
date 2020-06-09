package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   12:25
 */
public class L11_ContainerMostWater {
    // Solution, using two pointers, 谁矮移谁, we can get a larger area despite a reduction in width
    // Time complexity: O(n), space O(1)
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int curArea = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(curArea, maxArea);
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }
}
