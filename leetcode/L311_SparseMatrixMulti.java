package leetcode;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-04-20
    @time:   17:36
 */
public class L311_SparseMatrixMulti {
    public int[][] multiply(int[][] A, int[][] B) {
        // corner case


        int[][] C = new int[A.length][B[0].length];
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < C[0].length; j++) {
                // calculate the value of this cell
                // todo
                //  current row of A * current col of B
                int n = A[0].length;
                int total = 0;
                for (int k = 0; k < n; k++) {
                    total += A[i][k] * B[k][j];
                }

                C[i][j] = total;
            }
        }
        return C;
    }

    @Test
    public void test(){
        int[][] A = new int[][]{{1, 0, 0}, {-1, 0, 3}};
        int[][] B = new int[][]{{7, 0, 0}, {0, 0, 0}, {0, 0, 1}};
        int[][] C = multiply(A, B);
        System.out.println(arrayVisualizer.get2DIntArrayPrint(C));;
    }


}
