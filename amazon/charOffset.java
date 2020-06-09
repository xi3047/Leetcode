package amazon;
/**
 * Xi Zhang
 * 3/28/2019
 * Amazon Onsite Coding problem
 */

import org.junit.Test;

public class charOffset {
    public String offset(String s, int offset) {
        if (s == null) return null;
        int off = offset % 26;
        char[] charArr = s.toCharArray();
        for (int i = 0; i < charArr.length; i++) {
            Character ch = charArr[i];
            if (ch + off > 122) {
                int j = 'a' + off - 1;
                charArr[i] = (char) j;
                break;
            } else if (ch + off < 97) {
                int j = 'z' + off + 1;
                charArr[i] = (char) j;
                break;
            }
            charArr[i] += off;

        }
        return new String(charArr);
    }

    public static String offset2(String input, int offset) {
        char [] cs = input.toCharArray();

        boolean isNegative = offset < 0;
        int off = Math.abs(offset) % 26;

        for(int i=0;i<cs.length;i++) {
            int c = cs[i];
            if (isNegative) {
                c -= off;

                if (c<'a') {
                    c += 26;
                }
            } else {
                c += off;

                if (c > 'z') {
                    c -= 26;
                }
            }

            cs[i] = (char) c;
        }

        return new String(cs);
    }


    @Test
    public void test() {
        System.out.println(offset2("xyz", 1));
    }
}
