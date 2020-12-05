package round1;

public class arrayVisualizer {
    public static String get2DArrayPrint(boolean[][] matrix) {
        String output = new String();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output = output + (matrix[i][j] == true ? "T" : "F" + "\t");
            }
            output = output + "\n";
        }
        return output;
    }

    public static String get2DIntArrayPrint(int[][] matrix) {
        String output = new String();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                output = output + (matrix[i][j] + "\t");
            }
            output = output + "\n";
        }
        return output;
    }

    public static void main(String[] args) {
        boolean[][] okay = new boolean[5][4];
        int[][] nums = new int[][] {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};
        System.out.println(get2DArrayPrint(okay));
        System.out.println(get2DIntArrayPrint(nums));
    }
}
