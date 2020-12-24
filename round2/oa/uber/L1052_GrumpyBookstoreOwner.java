package round2.oa.uber;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/21/2020 2:26 AM
 * @topic round2.array  two pointer sliding window
 * @link
 * @description
 *
 * UBER interview question

 * Today, the bookstore owner has a store open for customers.length minutes.  Every minute, some number of
 * customers (customers[i]) enter the store, and all those customers leave after the end of that minute.
 *
 * On some minutes, the bookstore owner is grumpy.  If the bookstore owner is grumpy on the i-th minute, grumpy[i] = 1,
 * otherwise grumpy[i] = 0.  When the bookstore owner is grumpy, the customers of that minute are not satisfied,
 * otherwise they are satisfied.
 *
 * The bookstore owner knows a secret technique to keep themselves not grumpy for X minutes straight, but can only use it once.
 *
 * Return the maximum number of customers that can be satisfied throughout the day.
 *
 * Example 1:
 *
 * Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * Output: 16
 * Explanation: The bookstore owner keeps themselves not grumpy for the last 3 minutes.
 * The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 *
 */
public class L1052_GrumpyBookstoreOwner {
    /*
     * Solution explanation
     * https://www.youtube.com/watch?v=1MF0Q4hpsTM
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int x) {
        int len = customers.length;
        int sum = 0;
        for (int i = 0; i < len; i++) {
            if (grumpy[i] != 1) sum += customers[i];
            else grumpy[i] =  customers[i];
        }
        int save = 0;
        for (int i = 0; i < x; i++) {
            save += grumpy[i];
        }

        int max = save;

        for (int i = x; i < len; i++) {
            save -= grumpy[i - x];
            save += grumpy[i];
            max = Math.max(max, save);
        }

        return sum + max;
    }

    @Test
    public void test() {
        int[] arr = {2,4,1,5,2,6,7};
        int[] algo = {0,1,0,0,1,0,0};
        System.out.println(maxSatisfied(arr,algo,4));
    }

}
