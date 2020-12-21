package round2.oa.uber;

/**
 * @author Xi Zhang
 * @date 12/21/2020 12:49 AM
 * @topic round2.oa
 * @link
 * @description
 * uber oa
 */
public class reverseTwoDigits {
    public static int reverseTwoDigits(int num) {
        int length = 0;
        long temp = 1;
        while (temp <= num) {
            length++;
            temp *= 10;
        }
        int res = 0;
        int n = length / 2;
        while (n-- > 0) {
            int y = num % 10;
            int x = (num / 10) % 10;
            res = res * 10 + y;
            res = res * 10 + x;
            num = num /100;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(reverseTwoDigits(4239));
    }
}
