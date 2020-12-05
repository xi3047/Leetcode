package round1;

import java.util.ArrayList;
import java.util.List;

/*
    @author: Xi Zhang
    @date:   2019-05-06
    @time:   21:34

 */

/**
 * DFS using Vertex class
 */
public class L207_CourseScheduleV2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Initialize new Vertex array
        V [] array = new V[numCourses];
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
