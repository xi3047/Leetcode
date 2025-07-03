package round4.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L249_GroupShiftedStrings {
    public List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String key = compute(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        for (List<String> group : map.values()) {
            res.add(new ArrayList<>(group));
        }
        return res;
    }

    private String compute(String s) {
        if (s.length() == 1) return "single";
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int diff = (s.charAt(i) - s.charAt(i - 1) + 26) % 26;
            sb.append(diff + ".");
        }
        return sb.toString();
    }
}
