package round2.oa.airbnb;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * @author Xi Zhang
 * @date 1/14/21 2:35 PM
 * @topic round2.oa.airbnb
 * @link
 * @description AirBnB interview question
 * If a number is odd, the next transform is 3*n+1
 * If a number is even, the next transform is n/2
 * The number is finally transformed into 1.
 * The step is how many transforms needed for a number turned into 1.
 * Given an integer n, output the max steps of transform number in [1, n] into 1.
 */
public class CollatzConjecture {
    /**
     * Recursion
     */
    public class Solution {
        private int findSteps(int num) {
            if (num <= 1) return 1;
            if (num % 2 == 0) return 1 + findSteps(num / 2);
            return 1 + findSteps(3 * num + 1);
        }

        public int findLongestSteps(int num) {
            if (num < 1) return 0;

            int res = 0;
            for (int i = 1; i <= num; i++) {
                int t = findSteps(i);
                res = Math.max(res, t);
            }

            return res;
        }
    }
    /**
     * Recursion with memoization
     */
    public class Solution2 {
        Map<Integer, Integer> map = new HashMap<>();

        private int findSteps(int num) {
            if (num <= 1) return 1;
            if (map.containsKey(num)) return map.get(num);
            if (num % 2  == 0) num /= 2;
            else num = num * 3 + 1;
            //if (map.containsKey(num)) return map.get(num) + 1;
            int t = findSteps(num);
            map.put(num, t);
            return t + 1;
        }
        public int findLongestSteps(int num) {
            if (num < 1) return 0;

            int res = 0;
            for (int i = 1; i <= num; i++) {
                int t = findSteps(i);
                map.put(i, t);
                res = Math.max(res, t);
            }
            return res;
        }
    }
    public static class UnitTest {
        @Test
        public void test1() {
            Solution sol = new CollatzConjecture().new Solution();
            assertEquals(1, sol.findLongestSteps(1));
            assertEquals(20, sol.findLongestSteps(10));
            assertEquals(112, sol.findLongestSteps(37));
            assertEquals(119, sol.findLongestSteps(101));
        }

        @Test
        public void test2() {
            Solution2 sol = new CollatzConjecture().new Solution2();
            assertEquals(1, sol.findLongestSteps(1));
            assertEquals(20, sol.findLongestSteps(10));
            assertEquals(112, sol.findLongestSteps(37));
            assertEquals(119, sol.findLongestSteps(101));
        }
    }

}
