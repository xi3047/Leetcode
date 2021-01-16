package round2.bitsMath;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 1/12/21 9:56 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/fraction-to-recurring-decimal/
 * @description
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * If multiple answers are possible, return any of them.
 *
 * It is guaranteed that the length of the answer string is less than 104 for all the given inputs.
 * Example 1:
 *
 * Input: numerator = 1, denominator = 2
 * Output: "0.5"
 * Example 2:
 *
 * Input: numerator = 2, denominator = 1
 * Output: "2"
 * Example 3:
 *
 * Input: numerator = 2, denominator = 3
 * Output: "0.(6)"
 * Example 4:
 *
 * Input: numerator = 4, denominator = 333
 * Output: "0.(012)"
 * Example 5:
 *
 * Input: numerator = 1, denominator = 5
 * Output: "0.2"
 */
public class L166_FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        StringBuilder sb = new StringBuilder();
        // check for negative result
        String sign = (numerator < 0 == denominator < 0 || numerator == 0) ? "" : "-";
        // prevent integer overflow
        long num  = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        sb.append(sign);
        sb.append(num / den);

        long remainder = num % den;
        if (remainder == 0) {
            return sb.toString();
        }
        sb.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (!map.containsKey(remainder)) {
                map.put(remainder, sb.length());
            } else {
                int idx = map.get(remainder);
                return sb.substring(0, idx) + "(" + sb.substring(idx) + ")";
            }
            remainder *= 10;
            sb.append(remainder/den);
            remainder = remainder % den;
        }

        return sb.toString();
    }

    @Test
    public void test() {
        System.out.println(fractionToDecimal(4, 333));
    }
}
