package round1;

import org.junit.Test;

import java.util.Stack;

public class L84_largestRecHistogram {
    public int largestRectangleArea(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }

    public int calculateArea(int[] heights, int start, int end) {
        if (start > end) return 0;
        int minIndex = start;
        for (int i = start; i <= end; i++) {
            if (heights[i] < heights[minIndex]) {
                minIndex = i;
            }
        }
        return Math.max(heights[minIndex] * (end - start + 1),
                Math.max(calculateArea(heights, minIndex + 1, end),
                        calculateArea(heights, start, minIndex - 1)));

    }


    public int largestRectangleAreaStack(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        // 栈里先塞进-1以作为左边界，当只有一个bar的时候，i = 1, i - (-1) - 1 = 1 就是这个bar的宽度
        stack.push(-1);
        for (int i = 0; i <= heights.length; ++i) {
            // 在遇到右边界时(遇到一个比当前bar小的），我们开始计算往左边看 利用peek获取当前bar的左边界，i - peek - 1 等于当前bar最大的宽度
            // 然后我们把当前的bar pop出来，乘以宽度，更新global max面积的值
            while (stack.peek() != -1 && (i == heights.length || heights[stack.peek()] >= heights[i])) {
                maxArea = Math.max(maxArea, heights[stack.pop()] * (i - stack.peek() - 1));
            }
            // 否者遇到更高的bar就继续塞进stack，因为可能取得更大的面积
            stack.push(i);
        }
        return maxArea;
    }

    @Test
    public void test() {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(largestRectangleAreaStack(heights));
    }
}



