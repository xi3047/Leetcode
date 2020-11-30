package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-04-14
    @time:   22:11

    Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class L121_BuySellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int min = prices[0];
        int maxP = 0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i], min);
            maxP = Math.max(prices[i] - min, maxP);
        }
        return maxP;
    }

    public int maxProfit2(int[] prices) {
        int maxCur = 0, max = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur+= prices[i] - prices[i-1]);
            max = Math.max(maxCur, max);
        }
        return max;
    }

    public int maxProfit3(int[] prices) {
        int cur = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            cur = Math.max(0, cur + prices[i] - prices[i - 1]);
            max = Math.max(max, cur);
        }
        return max;
    }

    public int[] maxProfitDay(int[] prices) {
        int cur = 0;
        int max = 0;

        int buyDay = 0;
        int sellDay = 0;

        for (int i = 1; i < prices.length; i++) {
            if (cur + prices[i] - prices[i - 1] > 0) {
                cur += prices[i] - prices[i - 1];
            } else {
                buyDay = i;
            }

            if (cur > max) {
                max = cur;
                sellDay = i;
            }

        }
        return new int[] {buyDay + 1, sellDay + 1};
    }

    public int maxProfitS(int[] prices) {
        int sell = 0; // dp 第 i 天 交易一次 不持股
        int buy = Integer.MIN_VALUE; // dp 第 i 天 交易一次 持股
        for (int price : prices) {
            sell = Math.max(sell, buy + price); // 今天不持股 = 昨天不持股，或 昨天持股加上今天继续持有，要加上今日股价 的更大的
            buy = Math.max(buy, -price); // 今天持股 = 昨天持股，或者 今天买入  (代表取最小的股）
        }
        return sell;
    }

    @Test
    public void test() {
        int[] prices = {7, 1 , 5, 3, 6, 4};
        System.out.println(maxProfitS(prices));
    }

    @Test
    public void testProfitDay() {
        int[] prices = {7, 3, 6, 8, 5, 1, 7, 2, 10};
        int[] days = maxProfitDay(prices);
        System.out.println("Buy  on day " + days[0] + " at price " + prices[days[0] - 1]);
        System.out.println("Sell on day " + days[1] + " at price " + prices[days[1] -1]);;
    }
}
