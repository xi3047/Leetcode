package leetcode;

import org.junit.Test;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-05-01
    @time:   20:18

    正着搜索 反着记， 可以丢弃 不是最短路径的边 和 不能到达终点的边
 */
public class L126_WordLadder2 {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> res = new ArrayList<>();
        if (beginWord == null || endWord == null || wordList == null) return res;

        Set<String> wordSet = new HashSet<>(wordList);
        boolean isOver = false;
        Queue<String> queue = new LinkedList<>();
        Map<String, List<String>> graph = new HashMap<>();


        queue.offer(beginWord);
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {

            int size = queue.size();
            Set<String> currentLevelSet = new HashSet<>();
            while (size-- > 0) {
                String cur = queue.poll();
                char[] currentChars = cur.toCharArray();
                // change the characters one by one and check if the word is in the dictionary
                for (int i = 0; i < currentChars.length; i++) {
                    // cache the character being changed
                    char curC = currentChars[i];
                    // change the character at index i to other 25 lowercase letters
                    for (char c = 'a'; c <= 'z'; c++) {
                        currentChars[i] = c;
                        String next = String.valueOf(currentChars);
                        if (wordSet.contains(next)) {
                            if (next.equals(endWord)) isOver = true;

                            if (!graph.containsKey(next)) {
                                graph.put(next, new ArrayList<>());
                            }
                            graph.get(next).add(cur);
                            if (currentLevelSet.add(next)) {
                                queue.offer(next);
                            }
                        }


                    }
                    //change back the character
                    currentChars[i] = curC;
                }
            }
            wordSet.removeAll(currentLevelSet);
            currentLevelSet.clear();
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
            List<String> copy = new LinkedList<>(curPath);
            res.add(copy);
            return;
        }
        List<String> next = graph.get(cur);

        for (String n : next) {
            curPath.add(0, n);
            search(res, curPath, n, end, graph);
            curPath.remove(0);
        }
    }

    @Test
    public void test() {
        String begin = "hit";
        String end = "cog";
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");
        List<List<String>> res = findLadders(begin, end, wordList);
        for (List<String> l : res) {
            System.out.println(Arrays.toString(l.toArray()));
        }

    }
}
