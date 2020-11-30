package round1;

/*
    @author: Xi Zhang
    @date:   2019-02-18
    @time:   19:21

    Solution: DFS fill all cells with original colors with new color
 */
public class L733_FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        floodFill(image, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void floodFill(int[][] image, int sr, int sc, int oldColor, int newColor) {
        if (sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != oldColor) return;
        image[sr][sc] = newColor;
        floodFill(image, sr + 1, sc, oldColor, newColor);
        floodFill(image, sr - 1, sc, oldColor, newColor);
        floodFill(image, sr, sc + 1, oldColor, newColor);
        floodFill(image, sr, sc - 1, oldColor, newColor);
    }
}
