package round2.string;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author Xi Zhang
 * @date 11/27/2020 9:46 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L151_ReverseWordsInString {
    /**
     * Hacks
     */
    public String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}
