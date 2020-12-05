package round1_misc.string;

import org.junit.Test;

/**
 * Remove leading/trailing/ and duplicate space with one remaining. Trim()
 */
public class  removeSpaces {
    public String removeSpaces(String s) {
        if (s == null || s.length() == 0) return s;
        int slow = 0;
        char[] chars = s.toCharArray();
        for (int fast = 0; fast < chars.length; fast++) {
            // 第一种写法
            if (chars[fast] == ' ' && (fast == 0 || chars[fast - 1] == ' ')){
                continue;
            } else {
                chars[slow++] = chars[fast];
            }

//            // 第二种写法
//            if (!(chars[fast] == ' ' && (fast == 0 || chars[fast - 1] == ' '))) {
//                chars[slow++] = chars[fast];
//            }

//            // 第三种写法
//            if (chars[fast] != ' ' || (fast != 0 && chars[fast - 1] != ' ')) { // 我是啥才保存下来？ 当 我不是空格 或者 我前面的也不是空格而且不是第一字符
//                chars[slow++] = chars[fast];
//            }
        }
        if (slow == 0) return "";
        return chars[slow - 1] == ' '? new String(chars, 0, slow - 1) : new String(chars, 0, slow);

    }

    @Test
    public void test() {
        String s = "   you   get  offer  ";
        System.out.println(removeSpaces(s));
    }
}
