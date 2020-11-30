package round1;

import java.util.ArrayList;
import java.util.List;

public class L282_ExpressionAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        dfs(res, new StringBuilder(), num, 0, target, 0, 0);
        return res;
    }

    void dfs(List<String> res, StringBuilder path, String input, int index, int target, int sum, int lastVal) {

        if (index == input.length()) {
            if (sum == target) {
                res.add(path.toString());
            }
            return;
        }
        for (int i = index + 1; i <= input.length(); i++) {
            int val = Integer.valueOf(input.substring(index, i));
            if (path.length() == 0) {
                path.append(val);
                dfs(res, path,input, i, target, val, val );
                //setback
            } else {
                path.append("+" + val);
                dfs(res, path, input, i, target, sum + val, val );
                //setback

                path.append("-" + val);
                dfs(res, path, input, i, target, sum - val, -val);
                //setback

                path.append("*" + val);
                dfs(res, path, input, i, target, sum -lastVal + lastVal* val, lastVal*val );
                //setback


            }
            if (val == 0) break;
        }
    }
}
