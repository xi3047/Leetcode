package leetcode;

/*
    @author: Xi Zhang
    @date:   2/12/19
    @time:   6:00 PM
 */
public class L509_Fibonacci {
    // Solution 1: recursion
    public static int fib(int n) {
        if (n==0) return 0;
        if (n==1) return 1;
        return fib(n - 1) + fib(n - 2);
    }

    // Solution 2: dp with O(n) space
    public static int fib2(int n) {
        if (n == 0) return 0;
        int[] array = new int[n + 1];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= n; i++) {
            array[i] = array[i-1] + array[i-2];
        }
        return array[n];
    }

    // Solution 3: dp with O(1) space
    public int fib3(int n) {
        if (n <= 1) return n;
        int a = 0, b = 1;
        while (n-- > 1) {
            int sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }
    public static void main(String[] args) {
        int x = fib(8);
        System.out.println(x);
    }
}
