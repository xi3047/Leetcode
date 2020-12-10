package round2.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/8/2020 2:01 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L244_ShortestWordDistance2 {
    Map<String, List<Integer>> map;
    List<Integer> word1Positions;
    List<Integer> word2Positions;

    public L244_ShortestWordDistance2(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            map.putIfAbsent(word, new ArrayList<>());
            map.get(word).add(i);
        }

    }

    public int shortest(String word1, String word2) {
        word1Positions = map.get(word1);
        word2Positions = map.get(word2);
        int min = Integer.MAX_VALUE;
        for (Integer i : word1Positions) {
            for (Integer j: word2Positions) {
                min = Math.min(min, Math.abs(i - j));
            }
        }
        return min;
    }
}
