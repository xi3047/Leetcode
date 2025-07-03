package round4.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1570_DotProductOfTwoSparseVectors {
    class SparseVector {
        Map<Integer,  Integer> map; // index to value map, only store nonzero values
        SparseVector(int[] nums) {
            map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    map.put(i, nums[i]);
                }
            }
        }

        public int dotProduct(SparseVector vec) {
            int res = 0;
            for (int index : map.keySet()) {
                if (vec.map.containsKey(index)) {
                    res += map.get(index) * vec.map.get(index);
                }
            }
            return res;
        }
    }


    /**
     * Follow up question to Sparse Vector
     *  0  1  2  3  4  5  6  7  8
     * [1, 1, 1, 3, 3, 3, 3, 6, 6]
     * From index 0 to 3 → value 1
     * From index 3 to 7 → value 3
     * From index 7 to 9 → value 6
     */
    class CompressedVector {
    List<Segment> segments;
        CompressedVector(int[][] ranges) {
            segments = new ArrayList<>();
            for (int[] range : ranges) {
                Segment segment = new Segment(range[0], range[1], range[2]);
                segments.add(segment);
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(CompressedVector vec) {
            int i = 0, j = 0;
            int res = 0;
            while (i < segments.size() && j < vec.segments.size()) {
                // find overlaps
                Segment a = segments.get(i);
                Segment b = segments.get(j);
                int startMax = Math.max(a.start, b.start);
                int endMin = Math.min(a.end, b.end);

                if (startMax < endMin) {
                    int overlapRange = endMin - startMax;
                    res += overlapRange * a.value * b.value;
                }

                if (a.end < b.end) {
                    i++;
                } else if (a.end > b.end) {
                    j++;
                } else {
                    i++;
                    j++;
                }
            }
            return res;
        }
    }
    class Segment {
        int start;
        int end;
        int value;

        Segment(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }
    }
}
