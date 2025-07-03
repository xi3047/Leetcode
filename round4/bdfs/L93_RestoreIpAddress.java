package round4.bdfs;

import java.util.ArrayList;
import java.util.List;

public class L93_RestoreIpAddress {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, res, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(String s, List<String> res, StringBuilder path, int count, int index) {
        if (count == 4 && index == s.length()) {
            res.add(path.toString());
            return;
        }

        int len = path.length();
        for (int i = 1; i <= 3 && index + i <= s.length(); i++) {
            String part = s.substring(index, index + i);
            if (part.length() > 1 && part.startsWith("0")) break;
            int val = Integer.parseInt(part);
            if (val >= 0 && val <= 255) {
                if (count < 3) {
                    path.append(val).append('.');
                } else {
                    path.append(val);
                }
                dfs(s, res, path, count + 1, index + i);
                path.setLength(len);
            }
        }
    }
}
