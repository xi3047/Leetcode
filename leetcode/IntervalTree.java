package leetcode;
/*
    Interval Tree只能解决true or false，不能how many

 */
public class IntervalTree {
    int start, end, max;
    IntervalTree left, right;

    public IntervalTree(int s, int e, int m) {
        start = s;
        end = e;
        max = m;
        this.left = null;
        this.right = null;

    }

    public boolean search(IntervalTree root, IntervalTree target) {
        IntervalTree cur = root;
        while (cur != null) {
            if (overlap(cur, target)) {
                return true;
            }
            if (cur.left != null && cur.left.max < target.start) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return false;
    }

    private boolean overlap(IntervalTree cur, IntervalTree target) {
        return false;
    }
}
