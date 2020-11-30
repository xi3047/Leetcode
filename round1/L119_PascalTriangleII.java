package round1;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-03-11
    @time:   22:41
    Example:

Input: 3
Output: [1,3,3,1]

 */
public class L119_PascalTriangleII {
    public static List<Integer> getRow2(int rowIndex) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(1);
        for (int i = 1; i <= rowIndex; i++) {
            for (int j = i - 1; j >= 1; j--) {
                int tmp = res.get(j - 1) + res.get(j);
                res.set(j, tmp);
            }
            res.add(1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getRow2(5));
    }
}
