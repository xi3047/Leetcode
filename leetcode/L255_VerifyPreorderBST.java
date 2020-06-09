package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-02-20
    @time:   22:08
 */
import java.util.Stack;

public class L255_VerifyPreorderBST {
    public boolean validate(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int pivot = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n < pivot) {
                return false;
            }
            while (!stack.isEmpty() && stack.peek() < n) {
                pivot = stack.pop();
            }
            stack.push(n);
        }
        return true;

    }
}
