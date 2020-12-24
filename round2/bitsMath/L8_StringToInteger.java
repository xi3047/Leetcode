package round2.bitsMath;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/23/2020 7:50 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/string-to-integer-atoi/
 * @description
 */
public class L8_StringToInteger {
    public int myAtoi(String s) {
        if (s.length() == 0) return 0;

        int i = 0, base = 0, sign = 1, n = s.length();

        // skip whitespaces
        while (i < n && s.charAt(i) == ' ') i++;
        // look for sign
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i++) == '+') ? 1 : -1;
        }
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && s.charAt(i) - '0' > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            base = base * 10 + (s.charAt(i++) - '0');
        }
        return base * sign;

    }
    @Test
    public void test() {
        System.out.println(myAtoi("2147483646"));
    }
}
