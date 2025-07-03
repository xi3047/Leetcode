package round4.array;

import java.util.*;

public class L3527_MostCommonResponse {
    public String findCommonResponse(List<List<String>> responses) {
        Map<String, Integer> map = new HashMap<>();
        for (List<String> list : responses) {
            Set<String> set = new HashSet<>(list);
            for (String response: set) {
                map.put(response, map.getOrDefault(response, 0) + 1);
            }
        }
        String res = "";
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String response = entry.getKey();
            int count = entry.getValue();

            if (count > maxCount || (count == maxCount && response.compareTo(res) < 0)) {
                maxCount = count;
                res = response;
            }
        }
        return res;
    }
}
