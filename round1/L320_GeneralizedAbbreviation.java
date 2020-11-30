package round1;
/*
    @author: Xi Zhang
    @date:   2019-05-08
    @time:   19:58

    Write a function to generate the generalized abbreviations of a word.

Note: The order of the output does not matter.

Example:

Input: "word"
Output:
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d",  "w3", "4"]
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class L320_GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), 0, word, 0);
        return res;
    }

    void dfs(List<String> res, StringBuilder path, int count, String word, int idx) {
        if (idx == word.length()) {
            res.add(path.toString() + (count > 0 ? count : ""));
            return;
        }
        // change to digit
        dfs(res, path, count + 1, word, idx + 1);

        // keep
        int len = path.length();
        char ch = word.charAt(idx);
        if (count > 0) {
            path.append(count);
        }
        path.append(ch);
        dfs(res, path, 0, word, idx + 1);
        path.setLength(len);
    }

    @Test
    public void test() {
        String s = "abc";
        System.out.println(generateAbbreviations(s));

    }
}
