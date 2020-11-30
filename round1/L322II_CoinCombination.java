package round1;
/*
    @author: Xi Zhang
    @date:   2019-03-04
    @time:   20:38

    Follow up question to leetcode 322,
    return all possible combination of coins that sum up to target amount


 */


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L322II_CoinCombination {
    // Solution 1 : using DFS backtracking
    public List<int[]> coinChange(int[] coins, int amount) {
        List<int[]> res = new ArrayList<>();
        int[] sol = new int[coins.length];
        dfs(coins, 0, amount, sol, res);
        return res;
    }

    private void dfs(int[] coins, int level, int left_balance,  int[] sol, List<int[]> res) {
        if (level == coins.length - 1) { // 当取到最后一个币种时候
            sol[level] = left_balance / coins[level]; // sol[3] 等于剩余cent
            if (left_balance % coins[level] == 0) { //如果取余最后为0，加进结果
                res.add(sol.clone());
            }
            return;
        }
        int num = left_balance / coins[level] + 1; // 当前币种可以取余的option, 加上不取的可能+1
        for (int i = 0; i < num; i++) {
            sol[level] = i; // 当前币种取的数量，从0 (不取）到最多可取的数量
            dfs(coins, level + 1, left_balance - i * coins[level], sol, res); // 余钱要减去 当前币种*取的数量
        }
    }

    private int countCoin(List<int[]> res) {
        int min = Integer.MAX_VALUE;
        for (int[] s : res) {
            int total = 0;
            for (int i : s) {
                total += i;
            }
            if (total < min) {
                min = total;
            }
        }
        return min;
    }

    @Test
    public void test() {

        int[] coins = new int[]{5, 2, 1};
        int amount = 11;
        List<int[]> res = coinChange(coins, amount);
        int i = 1;
        for (int[] r : res) {
            System.out.println(i + ": " +  Arrays.toString(r));
            i++;
        }
    }
}
