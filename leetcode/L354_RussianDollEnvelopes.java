package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   22:35

    You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

What is the maximum number of envelopes can you Russian doll? (put one inside other)

Note:
Rotation is not allowed.

Example:

Input: [[5,4],[6,4],[6,7],[2,3]]
Output: 3
Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

俄罗斯套娃， 先排序，然后用L300 的 binary search 做法
 */
public class L354_RussianDollEnvelopes {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, (a, b) -> a[0] == b[0] ? b[1] - a[1] : a[0] - b[0]);
        List<Integer> res = new ArrayList<>();
        for (int[] n : envelopes) {
            int number = n[1];
            if (res.size() == 0) {
                res.add(number);
            } else {
                if (res.get(res.size() - 1) < number) {
                    res.add(number);
                } else {
                    updateResult(res, number);
                }
            }
        }
        return res.size();
    }
    private void updateResult(List<Integer> res, int n) {
        int start = 0;
        int end = res.size() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            int midValue = res.get(mid);
            if (midValue == n) return;
            else if (midValue < n) start = mid + 1;
            else {
                end = mid - 1;
            }
        }
        res.set(start, n);
    }

    @Test
    public void test() {
        int[][] arr = {{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        maxEnvelopes(arr);
        for (int[] n : arr) {
            System.out.println(n[0] + ", "  + n[1]);


        }

    }
}
