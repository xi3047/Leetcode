package round4.string;

import java.util.Arrays;

public class L917_ReverseOnlyLetters {
    public String reverseOnlyLetters(String s) {
        char [] arr = s.toCharArray();
        int left = 0 , right = arr.length - 1;
        while (left < right) {
            while (left < right && !Character.isLetter(s.charAt(left))) left++;
            while (left < right && !Character.isLetter(s.charAt(right))) right--;

            char temp = s.charAt(left);
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;

        }
        return new String(arr);
    }


}
