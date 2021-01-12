package round2.string;

/**
 * @author Xi Zhang
 * @date 11/27/2020 7:51 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/add-strings/
 * @description Given two non-negative integers num1 and num2 represented as round1_misc.string, return the sum of num1 and num2.
 *
 * Note:
 *
 * The length of both num1 and num2 is < 5100.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 *
 */
public class L415_AddStrings {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        int i = num1.length() -1 , j = num2.length()-1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            int n1 = 0, n2 = 0;
            if (i >= 0) n1 = num1.charAt(i) - '0';
            if (j >= 0) n2 = num2.charAt(j) - '0';
            int sum = n1 + n2 + carry;
            carry = sum /10;
            sb.insert(0, sum % 10);
            i--;
            j--;
        }
        if (carry != 0) {
            sb.insert(0, carry);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String str = "dsafdsf";
        StringBuilder sb = new StringBuilder(str);

        System.out.println(sb.toString());
    }
}
