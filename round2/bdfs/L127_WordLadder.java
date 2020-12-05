package round2.bdfs;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/21/2020 7:41 PM
 * @topic round2.bdfs
 * @link
 * @description
 */
public class L127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) return -1;

        Set<String> wordSet = new HashSet<>(wordList);
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordSet.remove(beginWord);
        int distance = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
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
                        String str = String.valueOf(currentChars);
                        // if the wordSet contains the letter, add to queue and remove from the wordSet to deduplicate
                        if (wordSet.contains(str)) {
                            if (str.equals(endWord)) return distance+1;
                            queue.offer(str);
                            wordSet.remove(str);

                        }
                    }
                    //change back the character
                    currentChars[i] = curC;
                }
            }
            distance++;
        }
        return 0;
    }
}
