package round2.binarySearch;

/**
 * @author Xi Zhang
 * @date 11/7/2020 3:30 PM
 * @topic round2.binarySearch
 * @link
 */
public class L50_PowXN {
    public double myPow(double x, int n) {

        if (n == 0) return 1.0;
        else if (n == 1) return x;

        if (n < 0) {
            if(n == Integer.MIN_VALUE) {
                n += 2;
            }
            return myPow(1 / x, -n);
        }
        return n%2 == 0? myPow(x*x, n/2) : x * myPow(x*x, n/2);
    }

    public double myPow2(double x, int n) {
        double ans = 1;
        long absN = Math.abs((long)n);
        while (absN > 0) {
            if ((absN & 1) == 1) ans *= x;
            absN >>= 1;
            x *= x;
        }
        return n < 0 ? 1 / ans: ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println((1&1) == 1);
    }
}
