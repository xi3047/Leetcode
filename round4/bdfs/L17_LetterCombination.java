package round4.bdfs;

import java.util.*;

public class L17_LetterCombination {
    public List<String> convert (String s) {
        // 2 -> a b c
        // 3 -> d e f

        Map<Character, List<Character>> map = new HashMap<Character, List<Character>>();
        map.put('2', Arrays.asList('a', 'b', 'c'));
        map.put('3', Arrays.asList('d', 'e', 'f'));
        map.put('4', Arrays.asList('g', 'h', 'i'));
        map.put('5', Arrays.asList('j', 'k', 'l'));
        map.put('6', Arrays.asList('m', 'n', 'o'));
        map.put('7', Arrays.asList('p', 'q', 'r', 's'));
        map.put('8', Arrays.asList('t', 'u', 'v'));
        map.put('9', Arrays.asList('w', 'x', 'y', 'z'));

        List<String> res = new ArrayList<String>();
        dfs(s, res, map, 0, new StringBuilder());
        return res;
    }

    private void dfs(String s, List<String> res, Map<Character, List<Character>> map, int index, StringBuilder path) {
        if (index == s.length()) {
            res.add(path.toString());
            return;
        }
        Character ch = s.charAt(index);
        List<Character> list = map.get(ch);
        for (Character c : list) {
            path.append(c);
            dfs(s, res, map, index + 1, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        L17_LetterCombination c = new L17_LetterCombination();
        System.out.println(c.convert ("324"));
        // Time:  3^n
        // Space :n
    }
}
