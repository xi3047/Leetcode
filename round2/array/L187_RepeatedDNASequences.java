package round2.array;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/10/2020 12:22 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/repeated-dna-sequences/
 * @description All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 *
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 * Example 1:
 *
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 *
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 105
 * s[i] is 'A', 'C', 'G', or 'T'.
 */
public class L187_RepeatedDNASequences {

    /**
     * Use a set to dedup and a sliding window of size 10
     */
    public List<String> findRepeatedDnaSequences(String s) {
        if (s.length() < 10) return new ArrayList<>();
        Set<String> resSet = new HashSet<>();
        Set<String> set = new HashSet<>();
        set.add(s.substring(0, 10));
        StringBuilder sb = new StringBuilder();
        sb.append(s, 0, 10);
        for (int i = 10; i < s.length(); i++) {
            sb.deleteCharAt(0);
            sb.append(s.charAt(i));
            if (!set.add(sb.toString())) resSet.add(sb.toString());
        }
        return new LinkedList<>(resSet);
    }

    @Test
    public void test() {
        System.out.println(findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"));
    }
}


