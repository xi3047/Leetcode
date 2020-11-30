package round1_misc.amazon;
/**
 * Amazon 4/24/2019 Phone Interview Question 1
 */

import org.junit.Test;

public class replaceZeroWithFive {
    public int replaceZeroWithFive(int n) {
        if (n == 0) return 5;
        int res = 0;
        int digit = 1;
        while (n > 0) {
            int cur = n % 10;
            if (cur == 0) {
                cur = 5;
            }
            res += cur * digit;
            digit *= 10;
            n /= 10;
        }
        return res;
    }

    @Test
    public void test() {
        int n1 = 1024;
        int n2 = 5000;
        int n3 = -123400;

        System.out.println(replaceZeroWithFive(n1));
        System.out.println(replaceZeroWithFive(n2));
        System.out.println(replaceZeroWithFive(n3));
    }
}
