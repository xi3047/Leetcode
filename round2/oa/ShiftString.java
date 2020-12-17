package round2.oa;

/**
 * @author Xi Zhang
 * @date 12/16/2020 12:04 AM
 * @topic round2.oa
 * @link
 * @description
 */
public class ShiftString {
    public static String getShiftedString(String s, int leftShift, int rightShift) {
        if (leftShift > rightShift) {
            int left = leftShift - rightShift;
            while (left -- > 0) {
                s = shiftLeft(s);
            }
            return s;
        }  else if (rightShift > leftShift) {
            int right = rightShift - leftShift;
            while (right -- > 0) {
                s = shiftRight(s);
            }
            return s;
        }
        else {
            return s;
        }
    }

    public static String shiftLeft(String s) {
        return s.substring(1) + s.charAt(0);
    }

    public static String shiftRight(String s) {
        return s.charAt(s.length()-1) + s.substring(0, s.length()-1);
    }

    public static void main(String[] args) {
        System.out.println(getShiftedString("abcd", 3,1));
    }
}
