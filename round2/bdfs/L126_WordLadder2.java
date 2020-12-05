package round2.bdfs;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/22/2020 1:51 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/word-ladder-ii/
 * @description
 */
public class L126_WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || endWord == null || wordList == null) return res;
        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();
        boolean isOver = false;

        queue.offer(beginWord);
        wordSet.remove(beginWord);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Set<String> curLevelSet = new HashSet<>();
            while (size -- > 0) {
                String cur = queue.poll();
                char[] currentChars = cur.toCharArray();
                for (int i = 0; i < currentChars.length; i++) {
                    char curC = currentChars[i];
                    // change the character at index i to other 25 lowercase letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        currentChars[i] = c;
                        String next = String.valueOf(currentChars);
                        if (wordSet.contains(next)) {
                            if (next.equals(endWord)) isOver = true;

                            graph.putIfAbsent(next, new ArrayList<>());
                            graph.get(next).add(cur);
                            if (curLevelSet.add(next)) {
                                queue.offer(next);
                            }
                        }
                    }
                    currentChars[i] = curC;
                }
            }
            wordSet.removeAll(curLevelSet);
            curLevelSet.clear();
            if (isOver) {
                List<String> curPath = new LinkedList<>();
                curPath.add(endWord);
                search(res, curPath, endWord, beginWord, graph);
                return res;
            }
        }
        return res;
    }
    private void search(List<List<String>> res, List<String> curPath, String cur, String end, Map<String, List<String>> graph) {
        if (cur.equals(end)) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        List<String> nexts = graph.get(cur);
        for (String next : nexts) {
            curPath.add(0, next);
            search(res, curPath, next, end, graph);
            curPath.remove(0);
        }
    }
}
