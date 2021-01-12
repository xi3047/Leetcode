package round2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/27/2020 1:11 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L22_GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(res, path, n, 0, 0);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, int n, int left, int right) {
        if (left == right && right == n) {
            res.add(path.toString());
            return;
        }
        if (right > left || left > n) {
            return;
        }

        path.append("(");
        dfs(res, path, n, left + 1, right);
        path.setLength(path.length() - 1);

        path.append(")");
        dfs(res, path, n, left, right + 1);
        path.setLength(path.length() - 1);
    }
}
