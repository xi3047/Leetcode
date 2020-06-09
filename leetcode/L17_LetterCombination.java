package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-02-16
    @time:   22:33
    Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */


import java.util.ArrayList;
import java.util.List;

public class L17_LetterCombination {
    static String[] keypad = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) return res;
        backtrack(res, digits, new StringBuilder(), 0);
        return res;
    }

    private static void backtrack(List<String> res, String digits, StringBuilder sb, int index) {
        if (index == digits.length()) return;
        String letters = keypad[Character.getNumericValue(digits.charAt(index)) -2];
        for (int pos = 0; pos < letters.length(); pos++) {
            sb.append(letters.charAt(pos));
            if (sb.length() == digits.length()) {
                res.add(sb.toString());
            }
            backtrack(res, digits, sb, index + 1);
            sb.setLength(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> res = letterCombinations(digits);
        for (String s : res) {
            System.out.println(s);
        }

    }


}
