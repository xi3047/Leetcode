package round1_misc.string;

import org.junit.Test;

/**
 * Remove duplicates from sorted array with one left
 */
public class removeDuplicate {
    public String removeDuplicate(String s) {
        if (s == null || s.length() < 2) return s;
        int slow = 1;
        char[] arr = s.toCharArray();
        for (int fast = 1; fast < arr.length; fast++) {
            if (arr[slow - 1] == arr[fast]) continue;
            else {
                arr[slow++] = arr[fast];
            }
        }

        return new String(arr, 0, slow);
    }

    /**
     * remove with two left
     */
    public String removeDuplicate2(String s) {
        if (s == null || s.length() < 3) return s;
        int slow = 2;
        char[] arr = s.toCharArray();
        for (int fast = 2; fast < arr.length; fast++) {
            if (arr[slow - 2] == arr[fast]) continue;
            else {
                arr[slow++] = arr[fast];
            }
        }

        return new String(arr, 0, slow);
    }

    /**
     * remove with k left
     */
    public String removeDuplicatek(String s, int k) {
        if (s == null || s.length() < k + 1) return s;
        int slow = k;
        char[] arr = s.toCharArray();
        for (int fast = k; fast < arr.length; fast++) {
            if (arr[slow - k] == arr[fast]) continue;
            else {
                arr[slow++] = arr[fast];
            }
        }

        return new String(arr, 0, slow);
    }

    @Test
    public void test() {
        System.out.println(removeDuplicate2("aaaabbbbbcddeeeee"));
    }
}
