package round1_misc.OOD.amazon;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-02-23
    @time:   23:55

    Amazon OA question : reorder log file in lexicographical order
    Solution: 重写comparator, 比较每个单词
 */
public class reorderLog {
    public static List<String> reorder(int fileSize, List<String> logFile) {
        List<String> res = new ArrayList<>();
        if (logFile == null || logFile.size() == 0) return res;
        List<String> wordList = new ArrayList<>();
        List<String> numberList = new ArrayList<>();

        for (String s : logFile) {
            if (isNumber(s)) {
                numberList.add(s);
            } else {
                wordList.add(s);
            }
        }
        Collections.sort(wordList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] words1 = o1.split(" ");
                String[] words2 = o2.split(" ");
                int i = 1, j = 1;
                // compare word by word,
                while (i < words1.length && j < words2.length) {
                    String w1 = words1[i];
                    String w2 = words2[j];
                    if (w1.compareTo(w2) == 0) { // if the two words are the same, continue comparing the next pair
                        i++;
                        j++;
                    } else { // as soon as one word is in lower order, return
                        return w1.compareTo(w2);
                    }
                }
                // special cases when the prefix words are the same
                if (i == words1.length) return -1; // when first line has less words
                else if (j == words1.length) return 1; // when second line has less words
                else { // if they are of same length and contain exactly same words, compare the identifier instead
                    return words1[0].compareTo(words2[0]);
                }
            }
        });

        res.addAll(wordList);
        res.addAll(numberList);
        return res;
    }

    private static boolean isNumber(String s) {
        String[] words = s.split(" ");
        return words[1].charAt(0) >= '0' && words[1].charAt(0) <= '9';
    }
}
