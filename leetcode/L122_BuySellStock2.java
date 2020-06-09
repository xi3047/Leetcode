package leetcode;
/*
        @author: Xi Zhang
        @date:   2019-02-15
        @time:   14:15
        Say you have an array for which the ith element is the price of a given stock on day i.

        Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

        Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

        Example 1:

        Input: [7,1,5,3,6,4]
        Output: 7
        Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
                     Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
        Example 2:

        Input: [1,2,3,4,5]
        Output: 4
        Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
                     Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
                     engaging multiple transactions at the same time. You must sell before buying again.

     */
public class L122_BuySellStock2 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return -1;
        int maxCur = 0;
        int maxSum = 0;
        for (int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, prices[i] - prices[i-1]);
            maxSum += maxCur;
        }
        return maxSum;
    }

    public int maxProfitS(int[] prices) {
        int sold = 0, hold = Integer.MIN_VALUE;
        for (int price : prices) {
            int prevSold = sold;
            sold = Math.max(sold, hold + price);
            hold = Math.max(hold, prevSold - price);
        }
        return sold;
    }
}
