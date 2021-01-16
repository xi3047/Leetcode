package round2.design;

/**
 * @author Xi Zhang
 * @date 1/14/21 4:12 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/flatten-2d-vector/
 * @description
 * Design and implement an iterator to flatten a 2d vector. It should support the following operations: next and hasNext.
 *
 * Example:
 *
 * Vector2D iterator = new Vector2D([[1,2],[3],[4]]);
 *
 * iterator.next(); // return 1
 * iterator.next(); // return 2
 * iterator.next(); // return 3
 * iterator.hasNext(); // return true
 * iterator.hasNext(); // return true
 * iterator.next(); // return 4
 * iterator.hasNext(); // return false
 *
 * Notes:
 *
 * Please remember to RESET your class variables declared in Vector2D, as static/class variables are persisted across
 * multiple test cases. Please see here for more details.
 * You may assume that next() call will always be valid, that is, there will be at least a next element in the 2d
 * vector when next() is called.
 */
public class L251_Flatten2DVector {
    private int[][] vector;
    private int outer = 0;
    private int inner = 0;
    class Vector2D {
        public Vector2D(int[][] v) {
            vector = v;
        }

        public int next() {
            if (hasNext()) return vector[outer][inner++];
            else return -1;
        }

        public boolean hasNext() {
            while (outer < vector.length && inner == vector[outer].length) {
                outer++;
                inner = 0;
            }
            return outer < vector.length;
        }
    }
}
