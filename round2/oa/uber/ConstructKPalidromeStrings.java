package round2.oa.uber;

/**
 * @author Xi Zhang
 * @date 12/21/2020 9:43 PM
 * @topic round2.oa.uber
 * @link
 * @description
 */
public class ConstructKPalidromeStrings {
    public boolean canConstruct(String s, int k) {
        if (s.length() < k) return false;
        int[] count = new int[26];

        for (int c: s.toCharArray()) {
            count[c-'a']++;
        }
        int odd = 0;
        for (int c : count) {
            if (c %2 == 1) odd++;
        }

        return odd <= k;
    }
}
