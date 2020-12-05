package round1_misc.string;

/**
 * Given a round1_misc.string, remove character 'o' and 'f' from it.
 * Solution: using slow and fast pointer, save only the desired characters into chars[0...slow)
 */
public class removeChar {
    public String removeChar(String s) {
        if (s == null || s.length() == 0) return s;

        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] != 'o' && chars[fast] != 'f') {
                chars[slow] = chars[fast];
                slow++;
            }
        }
        return new String(chars, 0, slow);
    }
}
