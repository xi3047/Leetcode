package round2.string;

import java.util.Optional;

/**
 * @author Xi Zhang
 * @date 1/8/21 11:48 PM
 * @topic round2.string
 * @link https://leetcode.com/problems/reorganize-string/
 * @description
 */
public class L767_ReorganizeString {
    /**
     * Solution
     * count letter appearance and store in hash[i]
     * find the letter with largest occurence.
     * put the letter into even index numbe (0, 2, 4 ...) char array
     * put the rest into the array\
     */
    public String reorganizeString(String S) {
        int[] hash = new int[26];
        for (int i = 0; i < S.length(); i++) {
            hash[S.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        char [] res = new char[S.length()];
        int idx = 0;
        while (hash[letter] > 0) {
            res[idx] = (char) (letter  + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }
}
