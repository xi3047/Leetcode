package round4.array;

import java.util.Collections;

public class L973_KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {

        quickSelect(points, 0, points.length - 1, k - 1);
        int[][] res = new int[k][];
        for (int i = 0; i < k; i++) {
            res[i] = points[i];
        }
        return res;
    }

    private void quickSelect(int[][] points, int left, int right, int k) {
        int pivotDistance = distance(points[right]);
        int split = left;
        for (int i = left; i < right; i++) {
            if (distance(points[i]) < pivotDistance) {
                swap(points, i, split);
                split++;
            }
        }
        swap(points, split, right);
        if (split == k) return;
        else if (split < k) quickSelect(points, split + 1, right, k);
        else quickSelect(points, left, split - 1, k);
    }

    private int distance(int[] point) {
        int a = Math.abs(point[0]);
        int b = Math.abs(point[1]);
        return a * a + b * b;
    }

    private void swap(int[][] points, int i, int j) {
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
