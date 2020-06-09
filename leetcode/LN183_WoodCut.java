package leetcode;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   16:24

    LintCode 183 Wood Cut : https://www.lintcode.com/problem/wood-cut/description
    Description:
Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have
equal or more than k pieces with the same length. What is the longest length you can get from the n pieces of wood?
Given L & k, return the maximum length of the small pieces.

Example 1:
Input:
L = [232, 124, 456]
k = 7
Output: 114
Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.

 */
public class LN183_WoodCut {
    /**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        int maxLen = 0;
        for (int wood : L) {
            maxLen = Math.max(wood, maxLen);
        }
        int start = 1, end = maxLen;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (canCut(L, k, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return end;
    }

    private boolean canCut(int [] L, int k, int mid) {
        int sum = 0;
        for (int wood : L) {
            sum += wood / mid;
        }
        return sum >= k;
    }

    @Test
    public void test() {
        int[] L = {232, 124, 456};
        System.out.println(woodCut(L, 7));

    }
}
