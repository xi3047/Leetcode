package round4.string;

import java.util.HashSet;
import java.util.Set;

public class L3_LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        int n = s.length();
        int left = 0;
        Set<Character> set = new HashSet<>();
        for (int r = 0; r < n; r++) {
            Character curCh = s.charAt(r);
            while (set.contains(curCh)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(curCh);
            int width = r - left + 1;
            longest = Math.max(longest, width);
        }
        return longest;
    }

    public static void main(String[] args) {
        L3_LongestSubstringWithoutRepeatingCharacters solution = new L3_LongestSubstringWithoutRepeatingCharacters();
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
    }
}
