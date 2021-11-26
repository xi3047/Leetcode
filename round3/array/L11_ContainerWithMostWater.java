package round3.array;

/**
 * @author : Xi
 * @topic: array
 * @created : 11/23/2021, Tuesday, 13:28
 **/
public class L11_ContainerWithMostWater {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int curArea = 0, maxArea = 0;
        while (left < right) {
            curArea = Math.min(height[left], height[right]) * (right - left);
            if (curArea > maxArea) {
                maxArea = curArea;
            }
            if (height[left] < height[right]) left++;
            else right--;
        }
        return maxArea;
    }

}
