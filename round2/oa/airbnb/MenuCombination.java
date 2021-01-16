package round2.oa.airbnb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/15/21 12:52 AM
 * @topic round2.oa.airbnb
 * @link
 * @description
 */
public class MenuCombination {
    public List<List<Double>> getCombos(double[] prices, double target) {
        List<List<Double>> res = new ArrayList<>();
        int centsTarget = (int) Math.round(target * 100);
        Arrays.sort(prices);
        int[] centPrices = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            centPrices[i] = (int) Math.round(prices[i] * 100);
        }
        search(res, centPrices, 0, centsTarget, new ArrayList<>(), prices);
        return res;
    }

    private void search(List<List<Double>> res, int[] centPrices, int start,
                        int centsTarget, ArrayList<Double> curCombo, double[] prices) {
        if (centsTarget == 0) {
            res.add(new ArrayList<>(curCombo));
            return;
        }
        for (int i = start; i < centPrices.length; i++) {
            if (i > start && centPrices[i] == centPrices[i - 1]) {
                continue;
            }
            if (centPrices[i] > centsTarget) {
                break;
            }
            curCombo.add(prices[i]);
            search(res, centPrices, i + 1, centsTarget - centPrices[i], curCombo, prices);
            curCombo.remove(curCombo.size() - 1);
        }
    }

    public static void main(String[] args) {
        double a = 1.5;
        double b = 1.1213;
        double c = 2.6213;
        System.out.println(a + b == c);
    }
}
