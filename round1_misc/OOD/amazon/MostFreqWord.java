package round1_misc.OOD.amazon;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-02-23
    @time:   22:24

    Amazon oa question 7, literature linguistic
    Find the most frequent used word except the words to be excluded

 */
public class MostFreqWord {

    public static List<String> mostFreqWord(String text, String[] toExclude) {
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String word : toExclude) {
            set.add(word);
        }
        int max = 0;
        int count = 0;
        String[] words = text.split("[^A-Za-z]");
        for (String s : words) {
            if (!set.contains(s)) {
                map.put(s, map.getOrDefault(s, 0) + 1);
                count = map.get(s);
                max = Math.max(count, max);
            }

        }
        for (String s : map.keySet()) {
            if (map.get(s) == max) {
                res.add(s);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String text = "jack and jill went to the's market to buy bread and cheese's cheese is jack favorite food";
        String[] toExclude = {"and", "he", "the", "to", "is"};
        List<String> res  = mostFreqWord(text, toExclude);
        for (String s : res) {
            System.out.println(s);
        }

    }
}
