package round4.bdfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class L301_RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        Set<String> set = new HashSet<>();
        int rmL = 0, rmR = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                rmL++;
            } else if (ch == ')') {
                if (rmL > 0) rmL--;
                else rmR++;
            }
        }
        dfs(set, new StringBuilder(), s, 0, rmL, rmR, 0);
        return new ArrayList<>(set);
    }
    private void dfs(Set<String> res, StringBuilder path, String s, int index, int rmL, int rmR, int delta) {
        if (index == s.length()) {
            if (rmL == 0 && rmR == 0 && delta == 0) {
                res.add(path.toString());
                return;
            }
        }
        if (index == s.length() || rmL < 0 || rmR < 0 || delta < 0) {
            return;
        }

        char ch = s.charAt(index);
        int len = path.length();
        if (ch == '(') {
            // remove
            dfs(res, path, s, index + 1, rmL - 1, rmR, delta);
            // keep
            path.append(ch);
            dfs(res, path, s, index + 1, rmL, rmR, delta + 1);
            path.setLength(len);
        } else if (ch == ')') {
            // remove
            dfs(res, path, s, index + 1, rmL, rmR - 1, delta);
            // keep
            path.append(ch);
            dfs(res, path, s, index + 1, rmL, rmR, delta - 1);
            path.setLength(len);
        } else {
            path.append(ch);
            dfs(res, path, s, index + 1, rmL, rmR, delta);
            path.setLength(len);
        }
    }
}
