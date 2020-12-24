package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 12/13/2020 9:07 PM
 * @topic round2.bitsMath
 * @link https://leetcode.com/problems/valid-number/
 * @description
 */
public class L65_ValidNumber {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        for (int i = 0; i < s.length(); i++) {
            if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
                numberSeen = true;
            }  else if (s.charAt(i) == 'e') {
                if (eSeen || !numberSeen)
                    return false;
                numberSeen = false;
                eSeen = true;
            } else if (s.charAt(i) == '.') {
                if (eSeen || pointSeen)
                    return false;
                pointSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i - 1) != 'e')
                    return false;
            } else
                return false;
        }
        return numberSeen;
    }
}
