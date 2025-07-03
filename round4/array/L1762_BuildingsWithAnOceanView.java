package round4.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class L1762_BuildingsWithAnOceanView {
    /**
     * From left to right
     */
    public int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++) {
            // Remove all buildings that are blocked by the current one
            // keep stack in decreasing order
            while (!stack.isEmpty() && heights[i] >= heights[stack.peek()]) {
                stack.pop();
            }
            stack.push(i);
        }
        int[] res = new int[stack.size()];
        int i = stack.size() - 1;
        while (!stack.isEmpty()) {
            res[i--] = stack.pop();
        }
        return res;
    }

    /**
     * From right to left
     */
    public int[] findBuildings2(int[] heights) {
        List<Integer> res = new ArrayList<>();
        int tallest = 0;
        for (int i = heights.length - 1; i >= 0 ; i--) {
            if (heights[i] > tallest) {
                res.add(i);
                tallest = Math.max(tallest, heights[i]);
            }
        }
        Collections.reverse(res);
        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
