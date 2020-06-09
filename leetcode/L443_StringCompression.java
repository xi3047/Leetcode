package leetcode;

import org.junit.Test;

public class L443_StringCompression {
    public String compress(char[] chars) {
        if (chars == null) return null;
        int length = chars.length;
        int slow = 0, fast = 0;
        while ( fast < length) {
            char curChar = chars[fast];
            int count  = 0;
            while (fast < length && chars[fast] == curChar) {
                fast++;
                count++;
            }
            chars[slow++] = curChar;
            if (count != 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[slow++] = c;
                }
            }

        }
        return new String(chars, 0 ,slow);

    }
    public int compress2(char[] chars) {
        int indexAns = 0, index = 0;
        while(index < chars.length){
            char currentChar = chars[index];
            int count = 0;
            while(index < chars.length && chars[index] == currentChar){
                index++;
                count++;
            }
            chars[indexAns++] = currentChar;
            if(count != 1)
                for(char c : Integer.toString(count).toCharArray())
                    chars[indexAns++] = c;
        }
        return indexAns;
    }


    @Test
    public void test() {
        String s = "aaabbccc";
        char[] chars = s.toCharArray();
        System.out.println(compress(chars));
    }
}
