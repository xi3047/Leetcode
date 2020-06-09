package leetcode;

public class L714_StockTransactionFee {
    public int maxProfit(int[] prices, int fee) {
        int kSold = 0;
        int kHold = Integer.MIN_VALUE;

        for (int price : prices) {
            int prevSold = kSold;
            kSold = Math.max(kSold, kHold + price);
            kHold = Math.max(kHold, prevSold - price - fee);
        }
        return kSold;
    }
}
