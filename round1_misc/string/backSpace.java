package round1_misc.string;

import org.junit.Test;

public class backSpace {
    public boolean backspaceCompare(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int slow1 = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            if (sChars[fast] != '#') {
                sChars[slow1++] = sChars[fast];
            } else if (sChars[fast] == '#' && slow1 > 0) {
                slow1--;
            }
        }
        int slow2= 0;
        for (int fast = 0; fast < t.length(); fast++) {
            if (tChars[fast] != '#') {
                tChars[slow2++] = tChars[fast];
            } else if (tChars[fast] == '#' && slow2 > 0) {
                slow2--;
            }
        }
        if (slow1 != slow2) return false;
        for (int i = 0; i < slow1; i++) {
            if (sChars[i] != tChars[i]) return false;
        }
        return true;

    }

    @Test
    public void test() {
        System.out.println(backspaceCompare("ab#c", "ad#c"));
    }
}
