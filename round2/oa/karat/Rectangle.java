package round2.oa.karat;

/**
 * @author Xi Zhang
 * @date 1/25/2021 10:54 PM
 * @topic round2.oa.karat
 * @link
 * @description
 */
public class Rectangle {

    public static void main(String[] args) {
        int [][] Input = {
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 1},
                {1, 0, 1, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 1},
                {1, 1, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 0}
        };
        int [][] Input2 = {
                {1, 1, 1, 1},
                {1, 0, 0, 0},
                {1, 0, 1, 1},
                {1, 0, 1, 1},

        };

        Zeros(Input2);
    }

    public static void Zeros(int[][] matrix){

        int ROW = matrix.length;
        int COLUMN = matrix[0].length;

        boolean[][] visited = new boolean[ROW][COLUMN];
        for(int r =0; r < ROW; r++)
        {
            for(int c = 0; c < COLUMN;c++){

//                int startR = 0;
//                int startC = 0;

                if(matrix[r][c] == 0 && !visited[r][c]){

                    visited[r][c] = true;

                    int endR =0;
                    int endC =0;

                    for(int rr = r; rr < ROW; rr++){
                        if(matrix[rr][c] == 1)
                            break;
                        for(int cc = c;cc < COLUMN;cc++){
                            if(matrix[rr][cc] == 0){
                                endR = rr;
                                endC = cc;
                                visited[endR][endC] = true;
                            }
                            else
                                break;
                        }
                    }
                    System.out.print("["+ r +", "+ c +", "+endR+", "+endC+"] ,");
                }

            }
        }

    }

}
