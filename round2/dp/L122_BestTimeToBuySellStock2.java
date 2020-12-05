package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/28/2020 9:49 AM
 * @topic round2.dp
 * @link
 * @description
 */
public class L122_BestTimeToBuySellStock2 {
    public int maxProfit(int[] prices) {
        int sum = 0;
        int curMax = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            curMax = Math.max(0, prices[i + 1] - prices[i]);
            sum += curMax;
        }
        return sum;
    }
}
