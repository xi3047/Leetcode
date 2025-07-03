package round4.string;

public class L50_PowerXN {
    /**
     * Recursive
     */
    public double myPow(double x, int n) {
        // Convert n to long to avoid overflow when n == Integer.MIN_VALUE
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        if (N == 0) return 1.0;

        double half = myPow(x, (int)(N / 2));
        if (N % 2 == 0) {
            return half * half;
        } else {
            return x * half * half;
        }
    }


    /**
     * Iterative
     */
    public double myPowR(double x, int n) {
        long power = Math.abs((long)n);
        double result = 1.0;

        // x^1101
        while (power > 0) { // go through every bits in power
            if ((power & 1) == 1) { // check if last bit is one
                result = result * x;
            }
            x = x * x; // double x in next higher bit
            power = power >> 1;
        }

        return n >= 0 ? result : 1 / result;
    }

    public static void main(String[] args) {
        L50_PowerXN sol = new L50_PowerXN();
        System.out.println(sol.myPowR(2.0, 9));
    }
}
