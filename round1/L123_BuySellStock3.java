package round1;

import org.junit.Test;

public class L123_BuySellStock3 {
    public static int maxProfit(int [] prices) {
        int maxProfit1 = 0;
        int maxProfit2 = 0;
        int lowestBuyPrice1 = Integer.MAX_VALUE;
        int lowestBuyPrice2 = Integer.MAX_VALUE;

        for (int p : prices) {
            maxProfit2 = Math.max(maxProfit2, p - lowestBuyPrice2);
            lowestBuyPrice2 = Math.min(lowestBuyPrice2, p - maxProfit1);
            maxProfit1 = Math.max(maxProfit1, p - lowestBuyPrice1);
            lowestBuyPrice1 = Math.min(lowestBuyPrice1, p);
        }
        return maxProfit2;
    }

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        //highest profit in 0 ... i
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];

        // DP from left to right
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);
        }

        // DP from right to left
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);
        }

        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }

        return profit;
    }

    public int maxProfit3(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int len = prices.length;
        int[][] dp = new int[len + 1][5 + 1];
        dp[0][1] = 0;
        dp[0][2] = dp[0][3] = dp[0][4] = dp[0][5] = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++){
            for(int j = 1; j <= 5; j++){
                // status 1,3,5
                // 昨天没有持有股票 or 昨天持有股票今天卖掉
                if(j % 2 == 1){
                    dp[i][j] = dp[i-1][j];
                    if(i > 1 && j > 1 && dp[i-1][j-1] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + prices[i-1] - prices[i-2]);
                }
                // status 2,4
                // 昨天持有股票并且继续持有 or 昨天没有持有股票今天买入 or 昨天持有上一次的股票今天卖出并立即买入
                else{
                    dp[i][j] = dp[i-1][j-1];
                    if(i > 1 && j > 1 && dp[i-1][j] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i-1][j] + prices[i-1] - prices[i-2], dp[i][j]);

                    if(i > 1 && j > 2 && dp[i-1][j-2] != Integer.MIN_VALUE)
                        dp[i][j] = Math.max(dp[i-1][j-2] + prices[i-1] - prices[i-2], dp[i][j]);
                }
            }
        }
        return Math.max(dp[len][1], Math.max(dp[len][3], dp[len][5]));

    }

    public int maxProfit4(int[] prices) {
        int len = prices.length;
        int dp[][] = new int[len+1][5+1];
        // dp[0][1] = 0;
        // dp[0][2] = Integer.MIN_VALUE;
        // dp[0][3] = Integer.MIN_VALUE;
        // dp[0][4] = Integer.MIN_VALUE;
        // dp[0][5] = Integer.MIN_VALUE;
        for(int i = 1; i <= len; i++){

            System.out.print("Day" + (i - 1) + ": ");
            for(int j = 1; j <= 5; j++){
                //状态135今天不持有股票
                //昨天没有持有
                //昨天持有股票并卖了
                if(j%2 == 1){
                    //
                    dp[i][j] = dp[i-1][j];
                    if(i > 1 && j > 1){ //&& dp[i-1][j-1] != Integer.MIN_VALUE){
                        //卖了
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1] + prices[i-1] - prices[i-2]);
                    }
                }
                //24已经持有股票了
                //昨天不持有
                //昨天持有股票
                //昨天持有上一次的股票，今天卖了并买入
                else{
                    dp[i][j] = dp[i-1][j-1];//昨天没有，今天买入
                    if(i > 1 && j > 1){// && dp[i-1][j-1] != Integer.MIN_VALUE){
                        dp[i][j] = Math.max(dp[i][j],dp[i-1][j] + prices[i-1] - prices[i-2]);//昨天持有且今天还持有
                    }
                    //2卖了又买直接到4
                    if(i > 1 && j > 2){//&& dp[i-1][j-2] != Integer.MIN_VALUE){
                        dp[i][j] = Math.max(dp[i][j], dp[i-1][j-2] + prices[i-1]-prices[i-2]);
                    }
                }
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        // return Math.max(dp[len][1], Math.max(dp[len][3],dp[len][5]));
        return dp[len][5];
    }

    public int maxProfitS(int[] prices) {
        int ith1Sold = 0, ith2Sold = 0;
        int ith1Hold = Integer.MIN_VALUE, ith2Hold = Integer.MIN_VALUE;
        for (int price : prices) {
            ith2Sold = Math.max(ith2Sold, ith2Hold + price);
            ith2Hold = Math.max(ith2Hold, ith1Sold - price);
            ith1Sold = Math.max(ith1Sold, ith1Hold + price);
            ith1Hold = Math.max(ith1Hold, -price);
        }
        return ith2Sold;
    }


    @Test
    public void test() {
        int[] nums = {1,0,3,2,1,4};
        System.out.println(maxProfit4(nums));
    }
}
