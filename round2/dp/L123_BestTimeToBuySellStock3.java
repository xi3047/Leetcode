package round2.dp;

/**
 * @author Xi Zhang
 * @date 11/28/2020 2:44 PM
 * @topic round2.dp
 * @link
 * @description
 */
public class L123_BestTimeToBuySellStock3 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) return 0;
        int oneBuy = Integer.MIN_VALUE;
        int oneBuyOneSell = 0;
        int twoBuy = Integer.MIN_VALUE;
        int twoBuyTwoSell = 0;
        for(int p : prices) {
            twoBuyTwoSell = Math.max(twoBuyTwoSell, p + twoBuy);
            twoBuy = Math.max(twoBuy, oneBuyOneSell - p);
            oneBuyOneSell = Math.max(oneBuyOneSell, p + oneBuy);
            oneBuy = Math.max(oneBuy, -p);
        }
        return Math.max(oneBuyOneSell, twoBuyTwoSell);
    }

    public static void main(String[] args) {
        L123_BestTimeToBuySellStock3 solution = new L123_BestTimeToBuySellStock3();
        System.out.println(solution.maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
    }
}
