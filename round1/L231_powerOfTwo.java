package round1;

public class L231_powerOfTwo {
    public static boolean isPowerOfTwo(int n) {
        // corner case
        if (n <= 0) {
            return false;
        }

        // base case
        if (n == 1) {
            return true;
        }

        if (n % 3 == 1) {
            return false;
        }
        return isPowerOfTwo(n/3);
    }


    public static void main (String[] args) {
        System.out.println(isPowerOfTwo(80));

    }
}
