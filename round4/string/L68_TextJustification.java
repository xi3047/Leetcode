package round4.string;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class L68_TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>(); // Stores the fully justified lines.
        int i = 0, n = words.length; // i tracks current word index.

        while (i < n) {
            int j = i + 1; // j will point to the word beyond the last word that fits.
            int lineLength = words[i].length(); // Start with the first word's length.

            // Determine how many words fit in the current line.
            while (j < n && lineLength + words[j].length() + (j - i) <= maxWidth) {
                // (j - i) represents the number of spaces between words.
                lineLength += words[j].length();
                j++;
            }

            int totalSpaces = maxWidth - lineLength; // Spaces to distribute in the line.
            int wordsCount = j - i; // Number of words in the current line.

            if (wordsCount == 1 || j == n) {
                // If there’s only one word or it’s the last line, left-justify it.
                result.add(leftJustify(words, totalSpaces, i, j));
            } else {
                // Otherwise, fully justify the line by distributing spaces evenly.
                result.add(middleJustify(words, totalSpaces, i, j));
            }
            i = j; // Move to the next line.
        }
        return result;
    }

    // Method to justify a line in the middle (not last line, more than one word).
    private String middleJustify(String[] words, int totalSpaces, int i, int j) {
        int spaceGroups = j - i - 1; // Number of gaps between words.
        int spacesOnEachGroup = totalSpaces / spaceGroups; // Minimum spaces per gap.
        int extraSpaces = totalSpaces % spaceGroups; // Extra spaces to distribute from the left.

        StringBuilder sb = new StringBuilder(words[i]); // Start with the first word.
        for (int k = i + 1; k < j; k++) {
            // Add the appropriate number of spaces.
            int spacesToApply = spacesOnEachGroup + (extraSpaces-- > 0 ? 1 : 0);
            sb.append(" ".repeat(spacesToApply)); // Append the spaces.
            sb.append(words[k]); // Append the next word.
        }
        return sb.toString(); // Return the fully justified line.
    }

    // Method to left-justify a line (last line or line with one word).
    private String leftJustify(String[] words, int totalSpaces, int i, int j) {
        int spacesOnRight = totalSpaces - (j - i - 1); // Calculate spaces to pad at the end.

        StringBuilder sb = new StringBuilder(words[i]); // Start with the first word.
        for (int k = i + 1; k < j; k++) {
            sb.append(" "); // Single space between words.
            sb.append(words[k]);
        }
        sb.append(" ".repeat(spacesOnRight)); // Pad extra spaces at the end.
        return sb.toString(); // Return the left-justified line.
    }

    public static List<String> splitMessages(String s) {
        List<String> res = new ArrayList<>();
        String[] words = s.split(" ");
        int i = 0, n = words.length;
        StringBuilder sb = new StringBuilder();
        while (i < n) {
            int lineLength = words[i].length();
            sb.append(words[i]);
            int j = i + 1;
            while (j < n && lineLength + words[j].length() + (j - i) < 80) {
                lineLength += words[j].length();
                sb.append(" ").append(words[j]);
                j++;
            }
            res.add(sb.toString());
            sb = new StringBuilder();
            i = j;
        }
        int size = res.size();
        int index = 0;
        List<String> ans = new ArrayList<>();
        for (String msg : res) {
            sb = new StringBuilder();
            int position = index + 1;
            String suffix = " " + position + "/" + size;
            sb.append(msg).append(suffix);
            ans.add(sb.toString());
            index++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "Extra spaces between words should be distributed as evenly as possible. " +
                "If the number of spaces on a line does not divide evenly between words, the " +
                "empty slots on the left will be assigned more spaces than the slots on the right.";
        System.out.println(splitMessages(s));
    }

}
