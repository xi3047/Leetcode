package round2.bitsMath;

/**
 * @author Xi Zhang
 * @date 12/14/2020 4:35 PM
 * @topic round2.bitsMath
 * @link
 * @description
 */
public class test {
    public static int bracket_match(String bracket_string) {
        int leftB = 0;
        int rightB =0;
        for (char c : bracket_string.toCharArray()){
            if (c == '(') leftB++;
            else if (c == ')') {
                if (leftB > 0) leftB--;
                else rightB++;
            }
        }
        return leftB + rightB;
    }

    public static void main(String[] args) {
        System.out.println(bracket_match("())"));
    }
}
