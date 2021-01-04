package round2.graph;


import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/24/2020 11:43 PM
 * @topic round2.graph
 * @link https://leetcode.com/problems/course-schedule/
 * @description There are a total of numCourses courses you have to take, labeled from 0 to numCourses-1.
 *
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 *
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0. So it is possible.
 * Example 2:
 *
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 *              To take course 1 you should have finished course 0, and to take course 0 you should
 *              also have finished course 1. So it is impossible.
 */
public class L207_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize new Vertex array
        V[] array = new V[numCourses];
        for (int i = 0; i < numCourses; i++) {
            array[i] = new V(i);
        }

        // Build graph
        for (int i = 0; i < prerequisites.length; i++) {
            int prev = prerequisites[i][1], next = prerequisites[i][0];
            array[prev].nexts.add(next);
        }

        // check if the graph contains cycle
        for (int i = 0; i < numCourses; i++) {
            if (isCycled(array[i], array)) {
                return false;
            }
        }

        return true;
    }

    private boolean isCycled(V current, V[] array) {
        // pruning
        if (current.status == Status.DONE) return false;

        // contains cycle
        if (current.status == Status.PROCESSING) return true;

        current.status = Status.PROCESSING;
        for (int i : current.nexts) {
            if (isCycled(array[i], array)) {
                return true;
            }
        }
        // set to Done
        current.status = Status.DONE;
        return false;
    }

    private enum Status {
        INITIAL, PROCESSING, DONE;
    }

    private static class V {
        public int label;
        public List<Integer> nexts;
        public Status status;

        public V(int label) {
            this.label = label;
            this.nexts = new ArrayList<>();
            status = Status.INITIAL;
        }
    }
}
