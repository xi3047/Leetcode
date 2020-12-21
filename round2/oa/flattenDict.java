package round2.oa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/20/2020 8:50 PM
 * @topic round2.oa
 * @link
 * @description PRAMP interview practice question
 * !! Incorrect Solution !!
 */
public class flattenDict {
    static HashMap<String, String> flattenDictionary(Map<String, Object> dict) {
        // your code goes here
        HashMap<String, String> res = new HashMap<>();
        dfs(res, dict, new StringBuilder());
        return res;
    }

    private static void dfs(HashMap<String, String> res, Map<String, Object> dict, StringBuilder sb) {
        for (String key: dict.keySet()) {
            Object value = dict.get(key);
            if (value instanceof Map) {
                HashMap<String, Object> valueMap = (HashMap<String, Object>) value;
                if (key.length() == 0) dfs(res, valueMap, sb);
                else {
                    int length = sb.length();
                    dfs(res, valueMap, sb.append(key).append("."));
                    sb.setLength(length);
                }
            } else {
                res.put(sb.append(key).toString(), String.valueOf(value));
            }
        }
    }

    public static void main(String[] args) {
        Map<String, Object> test1 = Map.of(
                "", Map.of("a", "1"),
                "b", "3"
        );
        flattenDictionary(test1);


    }


}
