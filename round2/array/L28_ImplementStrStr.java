package round2.array;

/**
 * @author Xi Zhang
 * @date 12/25/2020 2:42 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L28_ImplementStrStr {
    public int strStr(String haystack, String needle) {
        // empty needle appears everywhere, first appears at 0 index
        if (needle.length() == 0)
            return 0;
        if (haystack.length() == 0)
            return -1;


        for (int i = 0; i < haystack.length(); i++) {
            // no enough places for needle after i
            if (i + needle.length() > haystack.length()) break;

            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i+j) != needle.charAt(j))
                    break;
                if (j == needle.length()-1)
                    return i;
            }
        }

        return -1;
    }
}
