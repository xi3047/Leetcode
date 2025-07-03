package round4.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L1868_ProductOfTwoEncodedArray {
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        List<List<Integer>> res = new ArrayList<>();
        int i = 0, j = 0;
        while (i < encoded1.length && j < encoded2.length) {
            int val1 = encoded1[i][0];
            int freq1 = encoded1[i][1];
            int val2 = encoded2[j][0];
            int freq2 = encoded2[j][1];

            int count = Math.min(freq1, freq2);
            int product = val1 * val2;

            List<Integer> lastEntry = res.isEmpty()? null : res.get(res.size() - 1);
            if (lastEntry != null && lastEntry.get(0) == product) {
                lastEntry.set(1, lastEntry.get(1) + count);
            } else {
                res.add(Arrays.asList(product, count));
            }

            encoded1[i][1] -= count;
            encoded2[j][1] -= count;

            if (encoded1[i][1] == 0) i++;
            if (encoded2[j][1] == 0) j++;
        }
        return res;
    }
}
