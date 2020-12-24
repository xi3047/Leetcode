package round2.oa.uber;

/**
 * @author Xi Zhang
 * @date 12/21/2020 12:49 AM
 * @topic round2.oa
 * @link
 * @description
 * uber oa
 * 0 1 2
 * 1 2 3 4
 *
 *
 * 1 2 3 4 5
 */
public class reverseTwoDigits {
    public static int reverseTwoDigits(int num) {
        int res = 0;
        char [] chars = String.valueOf(num).toCharArray();
        int i = 0;
        while (i < chars.length - 1) {
            int j = i + 1;
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i  = i + 2;
        }

        return Integer.parseInt(new String(chars));
    }

    public static void main(String[] args) {
        System.out.println(reverseTwoDigits(4239));
    }
}
