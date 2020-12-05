package round1;
/*
    @author: Xi Zhang
    @date:   2019-05-07
    @time:   18:22

    There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L210_CourseSchedule2V2 {
    public int[] findOrder(int numCourses, int[][] courses) {
        // Initialize graph
        Vertex[] array = new Vertex[numCourses];
        for (int i = 0; i < numCourses; i++) {
            array[i] = new Vertex(i);
        }

        // build graph
        for (int i = 0; i < courses.length; i++) {
            int preq = courses[i][1], next = courses[i][0];
            array[preq].nexts.add(next);
        }

        // check contains cycle
        List<Integer> res = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (containsCycle(res, array[i], array)) {
                return new int[0];
            }
        }
        // move result to int array result
        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }
        return ret;

    }

    private boolean containsCycle(List<Integer> res, Vertex cur, Vertex[] array) {
        if (cur.status == Status.DONE) {
            return false;
        }
        if (cur.status == Status.PROCESSING) {
            return true;
        }
        cur.status = Status.PROCESSING;
        for (int i : cur.nexts) {
            if (containsCycle(res, array[i], array)) {
                return true;
            }
        }
        res.add(0, cur.val);
        cur.status = Status.DONE;
        return false;
    }

    enum Status {
        INITIAL, PROCESSING, DONE;
    }

    class Vertex {
        public int val;
        public Status status;
        public List<Integer> nexts;

        public Vertex(int val) {
            this.val = val;
            this.status = Status.INITIAL;
            this.nexts = new ArrayList<>();
        }
    }

    @Test
    public void test(){
        int[][] preq = {{2,0}, {1,0}, {3,1}, {3,2}};
        int[] res = findOrder(4, preq);
        for (int n : res) {
            System.out.print(n);
        }
    }
}
