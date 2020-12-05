package round1;

/*
    @author: Xi Zhang
    @date:   2019-05-11
    @time:   18:49

    Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3
Explanation: transactions = [buy, sell, cooldown, buy, sell]
 */
public class L309_StockCooldown {
    public int maxProfit(int[] prices) {
        int kPrevSold = 0, kSold = 0, kHold = Integer.MIN_VALUE;

        for (int price : prices) {
            int oldSold = kSold; // 记录上次卖出时的最高利润
            kSold = Math.max(kSold, kHold + price); // 买入时不需要考虑cooldown
            kHold = Math.max(kHold, kPrevSold - price); // 卖出去 要隔一天才能买
            kPrevSold = oldSold;
        }
        return kSold;
    }
}
