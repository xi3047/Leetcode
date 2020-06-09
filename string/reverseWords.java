package string;

import org.junit.Test;

public class reverseWords {
    public String reverseWords(String s) {
        if (s == null || s.length() < 2) return s;
        char[] arr = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < s.length(); fast++) {
            if (arr[fast] == ' ' && (fast == 0 || arr[fast - 1] == ' ')) {
                continue;
            } else if (arr[fast] == ' ' && arr[fast - 1] != ' ') {
                reverseRange(arr, slow, fast - 1);
            }
            else if (fast == s.length() - 1) {
                reverseRange(arr, slow, fast);
            } else if (arr[fast] != ' ' && (fast == 0 || arr[fast - 1] == ' ')){
                slow = fast;
            }
        }
        return new String(arr);
    }

    public void reverseRange(char[] s, int left, int right) {
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    @Test
    public void test() {
        System.out.println(reverseWords("  you   get the  offer  "));
    }


}
