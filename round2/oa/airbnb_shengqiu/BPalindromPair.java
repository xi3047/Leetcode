package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. Input is a list of words, return a list of pair that can form a palindrom
2. The given list does not contain duplicate word
3. The given list may have empty string
4. Does not allow word to form palindrome with itself

Approach:
Two word can form a palindrome if one word is symetric with another, or symetric with
some prefix or postfix of another and the rest of another is a palindrom.
for example:
"abc","ba"  ba is symetric with prefix ab and c is a palindrome, therefore, the two words
can form a palindrome.
So, we can just store the reverse of all words in a hash table. Then for each word, try
all prefix and postfix, if prefix in the table and postfix is palindrom, then we find a
pair. If postfix in the table and prefix is palindrome, we also find a pair.

Time: O(n*l^2)
O(nl) to create map, O(n*l*l) to check
average length of each word: l
length of list: n
Space: O(n)
O(n) to store the reverse string
===================================
*/
public class BPalindromPair {
    public static void main(String[] args) {
        BPalindromPair sol = new BPalindromPair();
        List<String> words = Arrays.asList("abc", "ab", "ba", "cba" );
        List<List<String>> res = sol.findPair(words);
        System.out.println(res);
    }

    public List<List<String>> findPair(List<String> words) {
        Map<String, Integer> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        for (int i = 0; i < words.size(); i++) {
            map.put(reverse(words.get(i)), i);
        }
        for (int i = 0; i < words.size(); i++) {
            String cur = words.get(i);
            if (cur.equals("")) {   // if word itself is "", we need handle it specially
                for (int j = 0; j < words.size(); j++) {
                    if (i != j && isPalindrom(words.get(j))) {
                        res.add(Arrays.asList("", words.get(j)));
                    }
                }
            }
            for (int j = 0; j < cur.length(); j++) {
                String prefix = cur.substring(0, j);
                String postfix = cur.substring(j, cur.length());
                if (map.containsKey(prefix) && i != map.get(prefix) && isPalindrom(postfix)) {
                    res.add(Arrays.asList(words.get(i), reverse(prefix)));
                }
                if (map.containsKey(postfix) && i != map.get(postfix) && isPalindrom(prefix)) {
                    res.add(Arrays.asList(reverse(postfix), words.get(i)));
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
}
