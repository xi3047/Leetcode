package round2.string;

/**
 * @author Xi Zhang
 * @date 12/10/2020 10:16 PM
 * @topic round2.array
 * @link
 * @description
 */
public class L680_ValidPalidrome2 {
    public boolean validPalindrome(String s) {
        int l = 0;
        int r = s.length()-1;
        while (l<r){
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
        while (s < t){
            if (str.charAt(s++) != str.charAt(t--)) return false;
        }
        return true;
    }
}
