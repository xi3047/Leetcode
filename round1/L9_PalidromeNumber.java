package round1;

/*
    @author: Xi Zhang
    @date:   2019-05-30
    @time:   18:04
 */
public class L9_PalidromeNumber {
    public boolean isPalidrome(int x) {
        if (x < 0) return false;
        int xCopy = x;
        int temp = 0;
        while (xCopy > 0) {
            temp = temp * 10 + xCopy % 10;
            xCopy /= 10;
        }
        return temp == x;
    }
}
