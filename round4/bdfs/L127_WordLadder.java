package round4.bdfs;

import java.util.*;

public class L127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> wordSet = new HashSet<>(wordList);
        int distance = 1;
        queue.offer(beginWord);
        wordSet.remove(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                char[] charArray = cur.toCharArray();
                for (int i = 0; i < cur.length(); i++) {
                    char curC = charArray[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        charArray[i] = c;
                        String newWord = String.valueOf(charArray);
                        if (wordSet.contains(newWord)) {
                            if (newWord.equals(endWord)) {
                                return distance + 1;
                            }
                            queue.offer(newWord);
                            wordSet.remove(newWord);
                        }
                    }
                    charArray[i] = curC;
                }
            }
            distance++;

        }
        return 0;
    }
}
