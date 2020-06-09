package leetcode;

/*
    @author: Xi Zhang
    @date:   2/5/19
    @time:   4:20 PM
 */
public class L42_TrappingRain {
    public int trap(int[] height) {
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) {
                water += (height[left] - leftMax);
                left++;
            } else {
                water += (height[right] - rightMax);
                right--;
            }
        }
        return water;
    }
}
