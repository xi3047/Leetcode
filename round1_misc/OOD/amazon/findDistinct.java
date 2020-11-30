package round1_misc.OOD.amazon;

import org.junit.Test;

import java.util.*;

public class findDistinct {
    public List<String> findDistinct(List<String> input) {
        List<String> res = new ArrayList<>();
        if (input == null || input.size() == 0) return res;
        List<String> resList = new ArrayList<>();

        Map<String, Integer> wordToFrequency = new HashMap<>();
        Set<String> set = new HashSet<>();

        for (String s : input) {
            String s2 = s.toLowerCase();
            List<String> words = Arrays.asList(s2.split("\\s+"));
            set.addAll(words);
            for (String i : set) {
                wordToFrequency.put(i, wordToFrequency.getOrDefault(i, 0) + 1);
            }
            set.clear();
        }

        for (String key : wordToFrequency.keySet()) {
            if (wordToFrequency.get(key) == 1) {
                resList.add(key);
            }
        }

        return resList;
    }



    @Test
    public void test() {
        String s1 = "It was sunny this weekend";
        String s2 = "It was raining this weekend";
        String s3 = "It was cloudy Cloudy this weekend";
        List<String> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        System.out.println(findDistinct(list));
    }
}
