package round2.oa.pureStorage;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 1/23/2021 12:02 AM
 * @topic round2.oa.pureStorage
 * @link
 * @description Pure Storage OA question
 */
public class FindRepetitions {
    public int maxConsecutiveRepetitions(String s1, String s2) {
        int count = 0;
        int max = 0;
        int j = 0;
        for (int i = 0; i < s2.length(); i++) {
            if (s2.charAt(i) != s1.charAt(j)) {
                count = 0;
            } else {
                j++;
            }
            if (j == s1.length()) {
                count++;
                max = Math.max(count, count);
                j = 0;
            }
        }
        return max;
    }

    @Test
    public void test() {
        String s1 = "AB";
        String s2 = "XABCDABABAB";
        System.out.println(maxConsecutiveRepetitions(s1, s2));
//        System.out.println(s2.substring(2).indexOf(s1));
    }
}
