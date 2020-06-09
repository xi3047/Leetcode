package leetcode;
/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
 */
import java.util.ArrayList;
import java.util.List;

public class L22_generateParenthesis {
    public static List<String> generateParenthesis(int n) {
    List<String> res = new ArrayList<>();
    StringBuilder path = new StringBuilder();
    dfs(res, path, 0 ,0, n );
    return res;
    }

    private static void dfs(List<String> res, StringBuilder path, int l, int r, int n) {
        if (l == r && r == n) {
            res.add(path.toString());
            return;
        }
        
        // 提前减枝
        if (l < r || l > n) {
            return;
        }
        // left
        path.append("(");
        dfs(res, path, l + 1, r, n);
        path.setLength(path.length() - 1);

        //right
        path.append(")");
        dfs(res, path, l, r + 1, n);
        path.setLength(path.length() - 1);
    }
    public static void main (String[] args) {
        System.out.print(generateParenthesis(3));
    }
}


// Time complexity:
// Space: