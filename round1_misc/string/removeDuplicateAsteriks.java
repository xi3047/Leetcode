package round1_misc.string;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-10
    @time:   01:35
    Part of Leetcode 44. Wildcard matching
    removing leading duplicate '*' from the pattern
    //todo to remove all duplciate '*' from the pattern

 */
public class removeDuplicateAsteriks {
    public String removeDuplicateAsteriks(String s) {
        //replace multiple * with one *
        //e.g a**b***c --> a*b*c
        char[] pattern = s.toCharArray();
        int writeIndex = 0;
        boolean isFirst = true;
        for ( int i = 0 ; i < pattern.length; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                }
            } else {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            }
        }
        return new String(pattern);
    }

    public String removeD(String s) {
        char[] arr = s.toCharArray();
        int slow = 1;
        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[slow - 1] == arr[fast] && arr[slow - 1] == '*') {
                continue;
            } else {
                arr[slow++] = arr[fast];
            }
        }
        return new String(arr, 0, slow);
    }

    @Test
    public void test() {
        String s = "***aa?**bb****";
        System.out.println(removeDuplicateAsteriks(s));
        System.out.println(removeD(s));
    }

}
