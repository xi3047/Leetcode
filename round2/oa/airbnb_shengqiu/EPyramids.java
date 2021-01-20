package round2.oa.airbnb_shengqiu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
Assumption:
1. input is a string represents the bottom, a list of string represents the allowed transition
2. return if we can reach top using the transition

Approach:
start from bottom, get all possible transition from this string to the next string. Then for
each of the next string, we will try it as the next level. Then we go to next level, continue
the same process, if we can finally reach the top, then the transition is true

Time:
time complexity to get all next string for each level takes O(k^n)
therefore, we can have k^n branches for each level, and we will have n levels
that is (k^n)^n * O(k^n)
Space: O(n)
for the recursion tree
 */
public class EPyramids {
    public static void main(String[] args) {

    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : allowed) {
            String key = str.substring(0,2);
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(str.substring(2));
        }
        return dfs(map, bottom);
    }

    private boolean dfs(Map<String, List<String>> map, String bottom) {
        if (bottom.length() == 1) {
            return true;
        }
        for (int i = 0; i < bottom.length() - 1; i++) {
            if (!map.containsKey(bottom.substring(i,i+2))) return false;
        }
        List<String> list = new ArrayList<>();
        getList(map, list, bottom, new StringBuilder(), 0);
        for (String s : list) {
            if (dfs(map, s)) return true;
        }
        return false;
    }

    private void getList(Map<String, List<String>> map, List<String> list, String bottom, StringBuilder sb, int index) {
        if (index == bottom.length() - 1) {
            list.add(sb.toString());
            return;
        }
        for (String s : map.get(bottom.substring(index, index + 2))) {
            sb.append(s);
            getList(map, list, bottom, sb, index + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
