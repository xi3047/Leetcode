package round2.array;

/**
 * @author Xi Zhang
 * @date 12/18/2020 12:12 AM
 * @topic round2.array
 * @link https://leetcode.com/problems/container-with-most-water/
 * @description
 */
public class L11_ContainerWithMostWater {
    public int maxArea(int[] height) {
            int left = 0;
            int right = height.length - 1;
            int maxArea = 0;
            while (left < right) {
                int curArea = Math.min(height[left], height[right]) * (right - left);
                maxArea = Math.max(maxArea,curArea);
                if (height[left] < height[right]) left++;
                else right--;
            }
            return maxArea;
    }
}
