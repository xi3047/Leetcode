package round2.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/22/2020 4:08 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/find-common-characters/
 * @description
 * Uber Interview Question
 *  Given an array A of strings made only from lowercase letters, return a list of all characters that show up in all strings within the list (including duplicates).  For example, if a character occurs 3 times in all strings but not 4 times, you need to include that character three times in the final answer.
 *
 * You may return the answer in any order.
 * Example 1:
 *
 * Input: ["bella","label","roller"]
 * Output: ["e","l","l"]
 * Example 2:
 *
 * Input: ["cool","lock","cook"]
 * Output: ["c","o"]
 */
public class L1002_FindCommonCharacters {
    public List<String> commonChars(String[] A) {
        List<String> res = new ArrayList<>();
        int[] count = new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);
        for (String str : A) {
            int[] currentCount = new int[26];
            str.chars().forEach(c -> ++currentCount[c - 'a']); // count each char's frequency in string str.
            for (int i = 0; i < 26; ++i) { count[i] = Math.min(currentCount[i], count[i]); } // update minimum frequency.
        }
        for (char c = 'a'; c <= 'z'; ++c) {
            while (count[c - 'a']-- > 0) { res.add("" + c); }
        }
        return res;
    }

    @Test
    public void test() {
        System.out.println(commonChars(new String[]{"bella", "label", "roller"}));
    }
}
