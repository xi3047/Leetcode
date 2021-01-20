package round2.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/6/2021 2:09 PM
 * @topic round2.array
 * @link https://leetcode.com/problems/text-justification/
 * @description
 * Given an array of words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * Note:
 *
 * A word is defined as a character sequence consisting of non-space characters only.
 * Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
 * The input array words contains at least one word.
 *
 *
 * Example 1:
 *
 * Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
 * Output:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * Example 2:
 *
 * Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
 * Output:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
 * Note that the second line is also left-justified becase it contains only one word.
 * Example 3:
 *
 * Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
 * Output:
 * [
 *   "Science  is  what we",
 *   "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 */
public class L68_TextJustfication {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int left = 0; List<String> result = new ArrayList<>();

        while (left < words.length) {
            int right = findRight(left, words, maxWidth);
            result.add(justify(left, right, words, maxWidth));
            left = right + 1;
        }

        return result;
    }

    public int findRight(int left, String[] words, int maxWidth) {

        int right = left;
        int sum = words[right++].length();

        while (right < words.length && sum + 1 + words[right].length() <= maxWidth) {
            sum += (1 + words[right++].length());
        }

        return right - 1;
    }

    public String justify(int left, int right, String[] words, int maxWidth) {
        if (left == right) {
            return padRight(words[left], maxWidth);
        }

        int sum = words[left].length();

        for (int i = left + 1; i <= right; i++) {
            sum += words[i].length();
        }

        boolean isLastLine = right == words.length - 1;
        int numWords = right - left;
        int numWhitespace = maxWidth - sum;

        int numSpacesBetween = isLastLine ? 1 : numWhitespace / numWords;
        int remainder = isLastLine ? 0 : numWhitespace % numWords;

        StringBuilder result = new StringBuilder();

        for (int i = left; i < right; i++) {
            result.append(words[i]);
            result.append(whitespace(numSpacesBetween));
            result.append(remainder-- > 0 ? " " : "");
        }

        result.append(words[right]);

        if (isLastLine) {
            return padRight(result.toString(), maxWidth);
        } else {
            return result.toString();
        }
    }

    public String whitespace(int numSpacesBetween) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numSpacesBetween; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

    public String padRight(String s, int maxWidth) {
        StringBuilder sb = new StringBuilder(s);
        sb.append(whitespace(maxWidth - s.length()));
        return sb.toString();
    }

}
