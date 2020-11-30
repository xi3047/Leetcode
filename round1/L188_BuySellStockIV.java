package round1;

import org.junit.Test;

import java.util.Arrays;

/**
 * We track two arrays, the local array tracks the maximum profit of j transactions and the last transaction is on the ith day.
 * the global array tracks the max profit of j transaction until ith day.
 */
public class L188_BuySellStockIV {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if ( k >= len / 2) {
            int sold = 0, hold = Integer.MIN_VALUE;
            for (int price : prices) {
                int prevSold = sold;
                sold = Math.max(sold, hold + price);
                hold = Math.max(hold, prevSold - price);
            }
            return sold;
        }

        // 第一个维度代表上一次交易的状态, i.e 第k次， 第k-1， 第k-2次
        int[] kSold = new int[k + 1];
        int[] kHold = new int[k + 1];
        Arrays.fill(kHold, Integer.MIN_VALUE);

        for (int price : prices) {
            for (int j = k; j > 0; j--) { // 从k次到0次的交易的状态都要一步步往回填
                kSold[j] = Math.max(kSold[j], kHold[j] + price);
                kHold[j] = Math.max(kHold[j], kSold[j - 1] - price);
            }
        }
        return kSold[k];
    }

    @Test
    public void test() {
        int[] prices = {1, 4, 5, 7, 6, 3, 2, 9};
        System.out.println(maxProfit(2, prices));

    }
}
