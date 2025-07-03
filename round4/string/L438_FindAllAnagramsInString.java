package round4.string;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L438_FindAllAnagramsInString {
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        Map<Character, Integer> pMap = new HashMap<>();
        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : p.toCharArray()) {
            pMap.put(c, pMap.getOrDefault(c, 0) + 1);
        }
        int k = p.length();
        for (int i = 0; i < k; i++) {
            char c = s.charAt(i);
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }
        if (sMap.equals(pMap)) {
            res.add(0);
        }

        for (int i = k; i < s.length(); i++) {
            char r = s.charAt(i);
            sMap.put(r, sMap.getOrDefault(r, 0) + 1);
            char l = s.charAt(i - k);
            sMap.put(l, sMap.get(l) - 1);
            if (sMap.get(l) == 0) {
                sMap.remove(l);
            }
            if (sMap.equals(pMap)) {
                res.add(i - k + 1);
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }

}
