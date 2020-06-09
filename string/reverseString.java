package string;

public class reverseString {

    // Solution 1: left right pointers
    public void reverseString(char[] s) {
        if(s == null || s.length < 2) return;
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    // Solution 2: recursion
    public String reverseRecurse(String s) {
        if (s == null || s.length() <= 1) return s;
        char[] arr = s.toCharArray();
        int left = 0, right = s.length() - 1;
        helper(arr, left, right);
        return new String(arr);
    }
    // recursion 1, do something then recurse
    private void helper(char[] arr, int left, int right) {
        if (left >= right) return;
        helper(arr, ++left, --right); // must use ++ before the variable
        swap(arr, left, right);
    }

    // recursion 2, recurse then do something
    private void helper2(char[] arr, int left, int right) {
        if (left >= right) return;
        helper(arr, left + 1, right + 1); // cannot use ++ here because it will change the values of the argument
        swap(arr, left, right);
    }

    void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
