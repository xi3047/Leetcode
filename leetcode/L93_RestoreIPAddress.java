package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-05-04
    @time:   11:10

    Given a string containing only digits, restore it by returning all possible valid IP address combinations.

Example:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class L93_RestoreIPAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), 0, 0, s);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, int num, int index, String input) {
        if (num == 4 && index == input.length()) {
            res.add(path.toString());
            return;
        }
        if (num == 4 && index < input.length()) return;

        for (int l = 1; l <= 3; l++) {
            if (index + l > input.length()) break;
            int val = Integer.valueOf(input.substring(index, index + l));
            String valS = String.valueOf(val);
            int len = valS.length();
            if (val >= 0 && val <= 255) {
                if (path.length() == 0) {
                    path.append(val);
                }
                else {
                    path.append("." + val);
                    len = len + 1;
                }
                dfs(res, path, num + 1, index + l, input);
                path.setLength(path.length() - len); // todo
            }
            if (val == 0) break;
        }

    }


    @Test
    public void test() {
        String input = "25525511135";
        System.out.println(restoreIpAddresses(input));
    }

}
