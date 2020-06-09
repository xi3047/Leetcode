package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-08
    @time:   01:14
 */
import org.junit.Test;

public class L344_ReverseString {
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) return s;
        char[] arr = s.toCharArray();
        int prev = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            if (arr[fast] == ' ') {
                reverseRange(arr, prev, fast - 1);
                prev = fast + 1;
            } else if (fast == s.length() - 1) {
                reverseRange(arr, prev, fast);
            }
        }
        return new String(arr);
    }

    public void reverseRange(char[] s, int left, int right) {
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    public void reverseString(char[] s) {
        if(s == null || s.length < 2) return;
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    public String reverseRecurse(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;
        helper(arr, left, right);
        return new String(arr);
    }

    private void helper(char[] arr, int left, int right) {
        if (left >= right) return;
        helper(arr, ++left, --right);
        swap(arr, left, right);
    }

    void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    @Test
    public void test() {
        System.out.println(reverseWords("Hello word Xi Zhang"));
    }

    @Test
    public void testRecurse() {
        System.out.println(reverseRecurse("abcdefg"));
    }

}
