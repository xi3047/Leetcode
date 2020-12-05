package round1;
/*
    @author: Xi Zhang
    @date:   2019-02-28
    @time:   17:41

    Reverse an integer
    For example 123 -> 321,  120 -> 21,  -213 -> -312
 */
import org.junit.Test;

public class L7_reverseInteger {
    public int reverse(int x) {
        int reversed = 0;

        while (x != 0) {
            int remainder = x % 10;
            int temp = reversed * 10 + remainder;


            // check for integer overflow, if the input is -2^31, reversed * 10 / 10 will not be equal to reversed
            if (temp / 10 != reversed) {
                return 0;
            }
            reversed = temp;
            x /= 10;
        }

        return reversed;
    }

    @Test
    public void test() {
        System.out.println(reverse(321));
    }
}
