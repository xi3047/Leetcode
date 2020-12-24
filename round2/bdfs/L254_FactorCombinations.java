package round2.bdfs;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/12/2020 11:53 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/factor-combinations/
 * @description
 */
public class L254_FactorCombinations {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        if (n < 4) {
            return results;
        }
        getFactors(n, 2, new ArrayList<Integer>(), results);
        return results;
    }

    private void getFactors(int n, int start, List<Integer> current, List<List<Integer>> results) {
        if (!current.isEmpty()) {
            current.add(n);
            results.add(new ArrayList<>(current));
            current.remove(current.size()- 1);
        }
        for (int i = start; i <= n / i; i++) {
            if (n % i == 0) {
                current.add(i);
                getFactors(n/i, i, current, results);
                current.remove(current.size()-1);
            }
        }
    }


    @Test
    public void test() {
        System.out.println(getFactors(8));
    }
}
