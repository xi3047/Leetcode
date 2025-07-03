package round4.bdfs;

import java.sql.Array;
import java.util.*;

public class L207_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Integer> inDegree = new HashMap<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
        }

        // build graph and indegree map
        for (int[] courses : prerequisites) {
            int course = courses[0];
            int preq = courses[1];
            graph.computeIfAbsent(preq, p -> new ArrayList<>()).add(course);
            inDegree.put(course, inDegree.get(course) + 1);
        }

        // offer 0 indegree nodes to queue
        Queue<Integer> queue = new LinkedList<>();
        for (Integer node: inDegree.keySet()) {
            if (inDegree.get(node) == 0) {
                queue.offer(node);
            }
        }
        int processed = 0;
        // go through graph
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            processed++;
            if (!graph.containsKey(cur)) continue;
            for (int next : graph.get(cur)) {
                inDegree.put(next, inDegree.get(next) - 1);
                if (inDegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        return processed == numCourses;
    }
}
