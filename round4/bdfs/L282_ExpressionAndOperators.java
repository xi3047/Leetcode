package round4.bdfs;

import java.util.ArrayList;
import java.util.List;

public class L282_ExpressionAndOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(res, num, target,  "",0, 0, 0);
        return res;
    }

    private void dfs(List<String> res, String num, int target, String path, int index, long sum, long lastValue) {
        if (index == num.length()) {
            if (sum == target) {
                res.add(path);
            }
            return;
        }

        long val = 0;
        for (int i = index; i < num.length(); i++) {
            if (i != index && num.charAt(index) == '0') { break;}
            val = val * 10 + num.charAt(i) - '0';
            if (index == 0) {
                dfs(res, num, target, path + val, i + 1,  val, val);
            } else {
                dfs(res, num, target, path +"+" + val, i + 1, sum + val, val);
                dfs(res, num, target, path + "-" + val, i + 1, sum - val, -val);
                dfs(res, num, target, path + "*" + val, i + 1, sum - lastValue + lastValue * val, lastValue * val);
            }
        }
    }
}
