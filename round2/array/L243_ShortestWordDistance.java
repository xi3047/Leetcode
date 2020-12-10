package round2.array;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 12/8/2020 11:13 AM
 * @topic round2.array
 * @link https://leetcode.com/problems/shortest-word-distance/
 * @description
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * Example:
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Input: word1 = “coding”, word2 = “practice”
 * Output: 3
 * Input: word1 = "makes", word2 = "coding"
 * Output: 1
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class L243_ShortestWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
            Map<String, List<Integer>> map = new HashMap<>();
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                map.putIfAbsent(word, new ArrayList<>());
                map.get(word).add(i);
            }
            List<Integer> word1Positions = map.get(word1);
            List<Integer> word2Positions = map.get(word2);
            int min = Integer.MAX_VALUE;
            for (Integer i : word1Positions) {
                for (Integer j: word2Positions) {
                    min = Math.min(min, Math.abs(i - j));
                }
            }
            return min;
    }
    /*
    Solution on discussion
     */
    public int shortestDistance2(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;

        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1))
                p1 = i;

            if (words[i].equals(word2))
                p2 = i;

            if (p1 != -1 && p2 != -1)
                min = Math.min(min, Math.abs(p1 - p2));
        }
        return min;
    }
}
