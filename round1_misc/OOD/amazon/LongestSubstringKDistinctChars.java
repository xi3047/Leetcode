package round1_misc.OOD.amazon;

import java.util.ArrayList;
import java.util.List;

public class LongestSubstringKDistinctChars {
    public static List<String> lengthOfLongestSubstringKDistinct(String inputStr, int num) {
        List<String> res = new ArrayList<>();
        //int[] visited = new int[26];
        int duplicates = 0;

        for (int k = 0; k < inputStr.length() - num + 1; k++) {
            // checking current window size
            int[] visited = new int[26];
            for (int i = k; i < num + k; i++) {
                char curChar = inputStr.charAt(i);
                visited[inputStr.charAt(i) - 'a']++;
                if (visited[inputStr.charAt(i) - 'a'] > 1) {
                    duplicates++;
                }
            }
            if (duplicates == 0) {
                res.add(inputStr.substring(k, k + num));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "round1_misc/OOD/amazon";
        List<String> res = lengthOfLongestSubstringKDistinct(s, 3);
        System.out.println(res);

    }
}
