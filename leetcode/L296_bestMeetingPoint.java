package leetcode;
/*
    @author: Xi Zhang
    @date:   2/3/19
    @time:   1:30 PM
 */
import java.util.*;

public class L296_bestMeetingPoint {
    public static int minTotalDistance(int[][] grid) {
        List<Integer> ipos = new ArrayList<Integer>();
        List<Integer> jpos = new ArrayList<Integer>();
        // 统计出有哪些横纵坐标
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 1){
                    ipos.add(i);
                    jpos.add(j);
                }
            }
        }
        int sum = 0;
        int midI = ipos.get(ipos.size() / 2);
        // 计算纵坐标到纵坐标中点的距离，这里不需要排序，因为之前统计时是按照i的顺序
        for(Integer pos : ipos){
            sum += Math.abs(pos - midI);
        }
        // 计算横坐标到横坐标中点的距离，这里需要排序，因为统计不是按照j的顺序
        Collections.sort(jpos);
        for(Integer pos : jpos){
            sum += Math.abs(pos - jpos.get(jpos.size() / 2));
        }
        return sum;
    }


    

    public static void main(String[] args) {
        int [][] grid = {{1,0,0,0,1},{0,0,0,0,0},{0,0,1,0,0}};
        System.out.println(minTotalDistance(grid));
    }
}
