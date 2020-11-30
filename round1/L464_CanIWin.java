package round1;

/*
    @author: Xi Zhang
    @date:   2019-05-11
    @time:   11:52
 */
public class L464_CanIWin {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        boolean[] balls = new boolean[maxChoosableInteger];
        return dfs(desiredTotal, 0, balls);
    }

    //
    private boolean dfs(int desiredTotal, int curSum, boolean[] balls) { // maxChoosable不大于20，可以用int来表示这个balls
        for (int i = 1; i < balls.length; i++) {
            if (balls[i]){ //这个球可以拿
                if (curSum + i > desiredTotal) {
                    return true;
                }
                balls[i] = false;
                boolean ret = dfs(desiredTotal, curSum + i, balls);
                balls[i] = true;
                if (!ret) {
                    return true;
                }
            }
        }
        return false;
    }
}
