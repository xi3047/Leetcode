package round1;

import org.junit.Test;

public class L302_SmallestRectangleEnclosingBlackPixels {
    public int minArea(char[][] image, int x, int y) {
        // corner case
        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) return 0;

        // check left boundary
        int start = 0, end = y;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (ithColAllZero(image, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int leftBoundary = start;

        // check right boundary
        start = y;
        end = image[0].length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (ithColAllZero(image, mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        int rightBoundary = end;


        // check top boundary
        start = 0;
        end = x;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (ithRowAllZero(image, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        int topBoundary = start;

        // check bottom boundary
        start = x;
        end = image.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (ithRowAllZero(image, mid)) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        int bottomBoundary = end;

        return (rightBoundary - leftBoundary + 1) * (bottomBoundary - topBoundary + 1);

    }

    private boolean ithRowAllZero(char[][] image, int mid) {
        for (int j = 0; j < image[0].length; j++) {
            if (image[mid][j] == '1') return false;
        }
        return true;
    }

    private boolean ithColAllZero(char[][] image, int mid) {
        for (int i = 0; i < image.length; i++) {
            if (image[i][mid] == '1') return false;
        }
        return true;
    }

    @Test
    public void test() {
        char[][] image = {{'0', '0', '0', '0', '1'}};
        System.out.println(minArea(image, 0 , 4));
    }
}
