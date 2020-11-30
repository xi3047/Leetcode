package round1_misc.amazon;
import org.junit.Test;

import java.util.*;

/**
 * Given a round1_misc.string, find the number of substring that contains a Char 'c' exactly 'k' number of times.
 * Input: str: abada
 * Character: 'a'
 * K: 2
 *
 * Output:
 * "aba", "abad", "bada" , "ada"
 */
public class findSubString {
    public List<String> findSubString(String str, Character c, int k) {
        List<String> res = new ArrayList<>();
        int left = 0;
        int right = 0;
        int cCount = 0;
        int i = 0;
        while ( true) {
            // 先check 当前window是否有k个character c，如果有的话，我们就加进结果集，我们就移动右指针扩大当前的window，直到当前window不valid为止
            if (str.charAt(right) == c) {
                cCount++;
            }
            if (cCount < k ){
                right++;
            } else if (cCount == k){
                String cur = str.substring(left, right + 1);
                res.add(cur);
                right++;
                // 当character c大于k次，我们要从左缩小当前窗口，就移动左一阵
            } else {
                if (str.charAt(left) == c) {
                    cCount--;
                }
                left++;
            }
            if ( left == str.length() - 1) break;
        }
        return res;
    }
    public static List<String> findSubString2(String str, Character c, int k) {
        List<String> res = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            int duplicateCount = k;
            for (int j = i; j < str.length(); j++) {
                char curChar = str.charAt(j);
                if (curChar == c) duplicateCount--;
                if (duplicateCount == 0) res.add(str.substring(i, j + 1));
            }

        }

        return res;
    }

    @Test
    public void test() {
        String str = "abada";
        Character c = 'a';
        int k = 2;
        System.out.println(findSubString(str, c, k));
    }
}
