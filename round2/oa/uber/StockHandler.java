package round2.oa.uber;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/24/2020 12:09 PM
 * @topic round2.oa.uber
 * @link
 * @description
 *
 * You are given prices, an array of positive integers where prices[i] represents the stock price
 * on the ith day. You'are also given algo, an array of 0 s and 1 s representing the bot's schedule,
 * where 0 means buy and 1 means sell.
 *
 * In order to improve the bot's performance, you'd like to choose a range of k consecutive days
 * where the bot will be programmed to sell; in other words, set a range of k consecutive elements
 * from algo to 1. Your task is to choose the interval such that it maximizes the bot's total revenue.
 * The revenue is defined as the sum of all selling prices minus the sum fo all buying prices.
 *
 * prices = 2, 4, 1, 5, 2, 6, 7
 * algo  =  0, 1, 0, 0, 1, 0, 0
 * k = 4
 *
 *
 */
public class StockHandler {
    public int maxStock(int[] prices, int[] algo, int k) {
        int len = prices.length;
        int max = 0;
        int cur = 0;
        //initialize window
        for (int i = 0; i < k; i++) {
            cur += prices[i];
        }
        max = cur;
        int left = 0, end = k;
        // move sliding window to get max
        for (int i = k; i < len; i++) {
            cur -= prices[i - k];
            cur += prices[i];
            if (cur > max) {
                max = cur;
                left = i - k + 1;
                end = i;
            }
        }
        for (int i = 0; i < left; i++) {
            if (algo[i] == 1) {
                max += prices[i];
            } else {
                max -= prices[i];
            }
        }
        for (int i = end + 1; i < len; i++) {
            if (algo[i] == 1) {
                max += prices[i];
            } else {
                max -= prices[i];
            }
        }
       return max;
    }

    @Test
    public void test() {
        System.out.println(maxStock(new int[]{2, 4, 1, 5, 2, 6, 7},
                new int[]{0, 1, 0, 0, 1, 0, 0}, 4));
    }
}
