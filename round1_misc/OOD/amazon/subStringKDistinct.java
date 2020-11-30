package round1_misc.OOD.amazon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 @author: Xi Zhang
 @date:   2019-07-02
 @time:   22:03 PM

 Amazon OA question 4

 **/
public class subStringKDistinct {

    public List<String> subStringKDistinct(String inputStr, int num) {
        if (inputStr == null || inputStr.length() < num) {
            return new ArrayList<>();
        }

        List<String> res = new ArrayList<>();
        int[] visited = new int[26];
        int duplicate = 0;
        for (int i = 0; i < inputStr.length(); i++) {
            char curChar = inputStr.charAt(i);
            if (++visited[curChar - 'a'] > 1) duplicate++;
            if (i >= num - 1) {
                if (i >= num) {
                    char charToRemove = inputStr.charAt(i - num);
                    if (--visited[charToRemove - 'a'] >= 1) duplicate--;
                }
                if (duplicate == 0) {
                    res.add(inputStr.substring(i - num + 1, i + 1));
                }
            }
        }
        return res;

    }

    @Test
    public void test(){
        String s = "love";
        System.out.println(subStringKDistinct(s, 3));

    }
}
