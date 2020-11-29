package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/28/2020 9:37 AM
 * @topic round2.dp
 * @link https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 * @description
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
