package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-03-04
    @time:   15:54
 */
public class L680_ValidPalidrome2 {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while (l<=r){
            if (s.charAt(l) == s.charAt(r)){
                l++;
                r--;
            }
            else{
                return isPalindrome(s,l,r-1) || isPalindrome(s,l+1,r);
            }
        }
        return true;
    }

    private boolean isPalindrome(String str, int s, int t){
        while (s <= t){
            if (str.charAt(s) == str.charAt(t)){
                s++;
                t--;
            }
            else
                return false;
        }

        return true;
    }

    @Test
    public void test() {
        String s = "abbcbba";
        System.out.println(isPalindrome(s, 0, s.length()-1));

    }
}
