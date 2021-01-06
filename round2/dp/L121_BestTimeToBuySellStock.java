package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/28/2020 9:37 AM
 * @topic round2.dp
 * @link https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * @description
 *
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
 *
 * Note that you cannot sell a stock before you buy one.
 *
 * Example 1:
 *
 * Input: [7,1,5,3,6,4]
 * Output: 5
 * Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
 *              Not 7-1 = 6, as selling price needs to be larger than buying price.
 * Example 2:
 *
 * Input: [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class L121_BestTimeToBuySellStock {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int maxProfit = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(prices[i - 1], min);
            int curProfit = prices[i] - min;
            maxProfit = Math.max(maxProfit, curProfit);
        }
        return maxProfit;
    }
}
