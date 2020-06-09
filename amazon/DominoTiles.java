package amazon;
/*
    @author: Xi Zhang
    @date:   2019-02-28
    @time:   20:30



    Google OA Feb 27, question 2

 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DominoTiles {
    public static int dominos(int[] A, int [] B) {
        if (A.length <1 || B.length < 1) return -1;
        if (A.length != B.length) return -1;

        HashMap<Integer, ArrayList<Integer>> top = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> bottom = new HashMap<>();
        for(int i=0;i<A.length;i++) {
            if (!top.containsKey(A[i])) {
                top.put(A[i], new ArrayList<>());
            }
            top.get(A[i]).add(i);
        }

        for(int i=0;i<B.length;i++) {
            if (!bottom.containsKey(B[i])) {
                bottom.put(B[i], new ArrayList<>());
            }
            bottom.get(B[i]).add(i);
        }

        int min = Integer.MAX_VALUE;
        for(Integer i: top.keySet()) {
            if (top.get(i).size() == A.length) return 0;
        }

        for(Integer i: bottom.keySet()) {
            if (bottom.get(i).size() == A.length) return 0;
        }

        for (int i = 1; i <= 6; i++) {
            if (top.get(i) == null || bottom.get(i) == null) continue;

            if (top.get(i).size() + bottom.get(i).size() >= A.length) {
                List<Integer> topIndices = top.get(i);
                List<Integer> bottomIndices = bottom.get(i);

                // check for duplicate indices
                int[] count = new int[A.length];
                int duplicates = 0;
                for(Integer j: topIndices) {
                    count[j]++;
                }
                for(Integer j: bottomIndices) {
                    if (count[j] >0) {duplicates++;}
                    count[j]++;
                }

                boolean notMatched = false;
                for(int c: count) {
                    if (c==0)  notMatched = true;
                }

                if (notMatched) continue;
                min = Math.min(min, Math.min(topIndices.size(),bottomIndices.size())-duplicates);
            }
        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }

        public static void main(String[] args) {
//        int[] a = {1, 2, 3, 6, 3, 2};
//        int[] b = {2, 1, 2 ,2 , 2, 4};
//        System.out.println(dominos(a, b));
        System.out.println(dominos(new int[]{2, 1, 3, 6}, new int[]{2, 1, 2, 2}));//-1

        System.out.println(dominos(new int[]{2, 2, 2, 2}, new int[]{1, 3, 1, 2}));//0

        System.out.println(dominos(new int[]{4, 4, 4, 3, 3}, new int[]{4, 3, 3, 4, 4}));//2

        System.out.println(dominos(new int[]{2, 2, 1, 2}, new int[]{2,2,1,2}));//-1

        System.out.println(dominos(new int[]{2, 2,2,1,3,2}, new int[]{2,4,5,2,2,1}));//2
    }
}
