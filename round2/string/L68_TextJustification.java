package round2.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/8/21 12:51 AM
 * @topic round2.string
 * @link https://leetcode.com/problems/text-justification/
 * @description Video Solution: https://www.youtube.com/watch?v=GqXlEbFVTXY&t=187s&ab_channel=MichaelMuinos
 */
public class L68_TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0, n = words.length;

        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();
            while (j < n && lineLength + words[j].length() + j - i - 1 < maxWidth) {
                lineLength += words[j].length();
                j++;
            }
            int totalSpaces = maxWidth - lineLength;
            int wordsCount = j - i;
            if (wordsCount == 1 || j >= n) result.add(leftJustify(words, totalSpaces, i, j));
            else result.add(middleJustify(words, totalSpaces, i, j));

            i = j;
        }
        return result;
    }

    private String middleJustify(String[] words, int totalSpaces, int i, int j) {
        int spaceGroups = j - i - 1;
        int spacesOnEachGroup = totalSpaces / spaceGroups;
        int extraSpaces = totalSpaces % spaceGroups;
        StringBuilder sb = new StringBuilder(words[i]);
        for (int k = i + 1; k < j; k++) {
            int spacesToApply = spacesOnEachGroup + (extraSpaces-- > 0 ? 1 : 0);
            sb.append(" ".repeat(spacesToApply) + words[k]);
        }
        return sb.toString();
    }

    private String leftJustify(String[] words, int totalSpaces, int i, int j) {
        int spacesOnRight = totalSpaces - (j - i - 1);
        StringBuilder sb = new StringBuilder(words[i]);
        for (int k = i + 1; k < j; k++) {
            sb.append(" " + words[k]);
        }
        sb.append(" ".repeat(spacesOnRight));
        return sb.toString();
    }

    @Test
    public void test() {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        System.out.println(fullJustify(words, 16));
    }
}
