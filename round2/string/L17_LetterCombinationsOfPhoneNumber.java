package round2.string;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 11/28/2020 3:28 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * @description
 */
public class L17_LetterCombinationsOfPhoneNumber {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        Map<String, List<Character>> map = new HashMap<>();
        map.put("2", Arrays.asList('a','b','c'));
        map.put("3", Arrays.asList('d','e','f'));
        map.put("4", Arrays.asList('g','h','i'));
        map.put("5", Arrays.asList('j','k','l'));
        map.put("6", Arrays.asList('m','n','o'));
        map.put("7", Arrays.asList('p','q','r','s'));
        map.put("8", Arrays.asList('t','u','v'));
        map.put("9", Arrays.asList('w','x','y','z'));
        dfs(res, map, new StringBuilder(), digits, 0);
        return res;
    }

    private void dfs(List<String> res, Map<String, List<Character>> map,  StringBuilder stringBuilder, String digits, int index) {
        if (index == digits.length()) {
            res.add(stringBuilder.toString());
            return;
        }

        List<Character> list = map.get(String.valueOf(digits.charAt(index)));
        for (Character ch: list) {
            stringBuilder.append(ch);
            dfs(res, map, stringBuilder, digits, index + 1);
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
    }
}
