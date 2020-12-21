package round2.oa.uber;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/18/2020 9:14 PM
 * @topic round2.oa
 * @link
 * @description Uber OA
 * Print Lexicographical Order of Diagonal In a Matrix starting from bottom left diagonal to top right.
 * This question is so stupid. Took me an hour. Imagine trying to finish this under 30 mins LOL.
 */
public class DiagonalOrder {
    public int[] diagonal (char[][] matrix) {
        int n = matrix.length;
        int size = 2 * n - 1;
        PriorityQueue<Diagnoal> minHeap = new PriorityQueue<>(size, new Comparator<Diagnoal>() {
            @Override
            public int compare(Diagnoal o1, Diagnoal o2) {
                int compareString = o1.diag.compareTo(o2.diag);
                if (compareString == 0) {
                    return o1.index- o2.index;
                } else {
                    return compareString;
                }
            }
        });
        String[] res = new String[2 * n - 1];
        int count = 0;
        for (int i = n - 1; i >= 0; i--) {
            int j = 0;
            char[] curD = new char[n];
            int i2 = i;
            while (i2 < n && j < n) {
                char c = matrix[i2][j];
                curD[j] = c;
                i2++;
                j++;
            }
            for (int k = j; k < n; k++) {
                curD[k] = curD[k - 1];
            }
            res[count++] = new String(curD);
        }
        for (int j = 1; j < n; j++) {
            int i = 0;
            char[] curD = new char[n];
            int j2 = j;
            while (i < n && j2 < n) {
                char c = matrix[i][j2];
                curD[i] = c;
                i++;
                j2++;
            }
            for (int k = i; k < n; k++) {
                curD[k] = curD[k - 1];
            }
            res[count++] = new String(curD);
        }
        for (int i = 0; i < res.length; i++) {
            minHeap.offer(new Diagnoal(res[i], i + 1));
        }
        int [] order = new int[size];
        for (int i = 0; i < size; i++) {
            order[i] = Objects.requireNonNull(minHeap.poll()).index;
        }
        return order;

    }
    static class Diagnoal {
        String diag;
        int index;
        public Diagnoal(String diag, int index) {
            this.diag = diag;
            this.index = index;
        }
    }


    @Test
    public void test() {
        char[][] matrix = new char[][] {
                {'a', 'c', 'a', 'b', 'b'},
                {'c', 'b', 'a', 'c', 'b'},
                {'a', 'a', 'e', 'c', 'b'},
                {'b', 'b', 'd', 'a', 'g'},
                {'a', 'b', 'e', 'b', 'a'}
        };
        int [] actual = diagonal(matrix);
        //IntStream.of(actual).forEach(System.out::println);
        int [] expected = {1,5,3,7,2,8,9,6,4};
        Assert.assertArrayEquals(expected, actual);

    }
}
