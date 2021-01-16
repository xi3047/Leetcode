package round2.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Xi Zhang
 * @date 1/14/21 11:07 PM
 * @topic round2.string
 * @link https://leetcode.com/problems/palindrome-pairs/
 * @description
 * Given a list of unique words, return all the pairs of the distinct indices (i, j) in the given list, so that the
 * concatenation of the two words words[i] + words[j] is a palindrome.
 *
 * Example 1:
 *
 * Input: words = ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * Example 2:
 *
 * Input: words = ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 * Example 3:
 *
 * Input: words = ["a",""]
 * Output: [[0,1],[1,0]]
 */
public class L336_PalindromePairs {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        for (String word : words) {
            int curIndex = map.get(word);
            String reversedWord = new StringBuilder(word).reverse().toString();
            // case 1, same length, care for corner case where word is palindrome itself
            if (map.containsKey(reversedWord) && curIndex != map.get(reversedWord)) {
                res.add(Arrays.asList(curIndex, map.get(reversedWord)));
            }
            // case 2, prefix
            for (String prefix : getValidPrefixes(word)) {
                String reversedPrefix = new StringBuilder(prefix).reverse().toString();
                if (map.containsKey(reversedPrefix)) {
                    res.add(Arrays.asList(curIndex, map.get(reversedPrefix)));
                }
            }
            // case 3, suffix
            for (String suffix : getValidSuffixes(word)) {
                String reversedSuffix = new StringBuilder(suffix).reverse().toString();
                if (map.containsKey(reversedSuffix)) {
                    res.add(Arrays.asList(map.get(reversedSuffix), curIndex));
                }
            }
        }
        return res;
    }

    private List<String> getValidSuffixes(String word) {
        List<String> validSuffixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, 0, i)) {
                validSuffixes.add(word.substring(i + 1, word.length())); // start at i + 1, for one letter palindrome
            }
        }
        return validSuffixes;
    }

    private List<String> getValidPrefixes(String word) {
        List<String> validPrefixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, i, word.length() - 1)) {
                validPrefixes.add(word.substring(0, i));
            }
        }
        return validPrefixes;
    }

    private boolean isPalindrome(String word, int left, int right) {
        while (left < right) {
            if (word.charAt(left) != word.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * Solution 2 outputting the strings instead
     */
    public List<List<String>> findPair(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            map.put(reverse(words[i]), i);
        }
        for (int i = 0; i < words.length; i++) {
            String cur = words[i];
//            if (cur.equals("")) {   // if word itself is "", we need handle it specially
//                for (int j = 0; j < words.length; j++) {
//                    if (i != j && isPalindrom(words[j])) {
//                        res.add(Arrays.asList("", words[j]));
//                    }
//                }
//            }
            for (int j = 0; j < cur.length(); j++) {
                String prefix = cur.substring(0, j);
                String suffix = cur.substring(j, cur.length());
                if (map.containsKey(prefix) && i != map.get(prefix) && isPalindrom(suffix)) {
                    res.add(Arrays.asList(words[i], reverse(prefix)));
                }
                if (map.containsKey(suffix) && i != map.get(suffix) && isPalindrom(prefix)) {
                    res.add(Arrays.asList(reverse(suffix), words[i]));
                }
            }
        }
        return res;
    }
    private String reverse(String input) {
        StringBuilder sb = new StringBuilder(input);
        return sb.reverse().toString();
    }
    private boolean isPalindrom(String input) {
        return input.equals(reverse(input));
    }


    @Test
    public void test() {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
//        System.out.println(palindromePairs(words));
        System.out.println(findPair(words));

    }
}
