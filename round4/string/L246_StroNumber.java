package round4.string;

import java.util.HashMap;
import java.util.Map;

public class L246_StroNumber {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put('0', '0');
        map.put('1', '1');
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');

        int left = 0, right = num.length() - 1;
        while (left <= right) {
            char lChar = num.charAt(left);
            char rChar = num.charAt(right);

            if (!map.containsKey(lChar) || map.get(lChar) != rChar) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
