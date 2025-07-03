package round4.array;

public class L121_BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int buy = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            buy = Math.min(prices[i - 1], buy);
            if (prices[i] > buy) {
                int curProfit = prices[i] - buy;
                maxProfit = Math.max(maxProfit, curProfit);
            }
        }
        return maxProfit;
    }
}
