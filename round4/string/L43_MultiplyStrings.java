package round4.string;

import java.math.BigInteger;

public class L43_MultiplyStrings {
    public String multiply(String num1, String num2) {
        // Edge case: if either number is zero
        if (num1.equals("0") || num2.equals("0")) return "0";

        int m = num1.length(), n = num2.length();
        int[] res = new int[m + n]; // max possible length

        // Reverse traversal (right to left)
        for (int i = m - 1; i >= 0; i--) {
            int d1 = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                int d2 = num2.charAt(j) - '0';
                int mul = d1 * d2;

                int digit = i + j + 1;
                int carry = i + j;

                int sum = mul + res[digit];

                res[digit] = sum % 10;
                res[carry] += sum / 10;
            }
        }
        // Convert result array to string, skipping leading zeros
        StringBuilder sb = new StringBuilder();
        for (int num : res) {
            if (sb.length() == 0 && num == 0) continue;
            sb.append(num);
        }
        return sb.toString();
    }
}
