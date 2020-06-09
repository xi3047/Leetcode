package leetcode;

public class L50_Power {
    // Solution 1: iterative, 平分法, credit: basketwang
    public static double power(double x, int power) {
        if (power == 0 || x == 1) return 1;
        if (power == 1) return x;
        // to handle case of MIN_VALUE, use -(power+1)
        if (power < 0) return 1/(x*power(x, -(power+1)));

        double res = 1.0;
        while (power > 1) {
            // if power is odd, multiply result by the number first then deal the power as even number
            if (power % 2 == 1) {
                res *= x;
            }
            x *= x;
            power /= 2;

        }
        res *= x;
        return res;
    }

    // Solution 2: Recursion

    public static double power2(double x, int power) {
        if (power == 0) return 1;
        if (power < 0) {
            //return 1 / (x* (power2(x, -(power+1))) );
            return 1 / (power2(x, -power));
        }
        double half = power2(x, power / 2);
        return power%2 == 0? half*half : half*half*x;
    }

    public static void main(String[] args) {
        System.out.println(power2(2.0, -2^31));
    }

}
