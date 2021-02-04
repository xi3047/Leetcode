package round2.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 2/1/21 5:13 下午
 * @topic round2.graph
 * @link https://leetcode.com/problems/sentence-similarity-ii/
 * @description
 */
public class L737_SentenceSimilarityII {
    public boolean areSentencesSimilarTwo(String[] words1, String[] words2, List<List<String>> pairs) {
        if (words1.length != words2.length) return false;
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : pairs) {
            String w1 = pair.get(0);
            String w2 = pair.get(1);
            String parent1 = find(map, w1);
            String parent2 = find(map, w2);
            if (!parent1.equals(parent2)) map.put(parent1, parent2);
        }
        for (int i = 0; i < words1.length; i++) {
            String w1 = words1[i];
            String w2 = words2[i];
            if (!w1.equals(w2) && !find(map, w1).equals(find(map, w2))) {
                return false;
            }
        }
        return true;
    }

    private String find(Map<String, String> map, String s) {
        if (!map.containsKey(s)) map.put(s, s);
        if (s.equals(map.get(s))) {
            return s;
        } else {
            return find(map, map.get(s));
        }
    }


}
