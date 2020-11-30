package round1;

import org.junit.Test;

import java.util.HashMap;

/*
    @author: Xi Zhang
    @date:   2019-03-09
    @time:   13:42
 */
public class L509_FibonacciNumber {

    // Solution 1, recursion.
    // Time complexity: O(n^2), cuz T(n) = T(n-1) + T(n-2), there is redundant computation
    // Space complexity: O(n), total number of stacks
    public int fib(int n){
        if (n <= 1) return n;
        return fib(n - 1) + fib(n - 2);
    }

    // Solution 2, dynamic programming
    // Time complexity: O(n)
    public int fibDP(int N) {
        if (N == 0) return 0;
        int[] dp = new int[N+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N];
    }

    // Solution 3 : recursion with memoization
    public int fibRM(int n) {
        HashMap<Integer, Integer> memoizedMap = new HashMap<>();
        memoizedMap.put(0, 0);
        memoizedMap.put(1, 1);
        return fibRM(n, memoizedMap);
    }

    private int fibRM(int n, HashMap<Integer, Integer> memoizedMap) {
        if (memoizedMap.containsKey(n)) {
            return memoizedMap.get(n);
        }
        int fibNum = fibRM(n - 1, memoizedMap) + fibRM(n - 2, memoizedMap);
        memoizedMap.put(n, fibNum);
        return fibNum;
    }

    @Test
    public void test() {
        System.out.println(fibDP(6));
    }

}
