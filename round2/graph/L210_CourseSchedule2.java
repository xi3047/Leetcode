package round2.graph;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/25/2020 2:13 PM
 * @topic round2.graph
 * @link
 * @description
 */
public class L210_CourseSchedule2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //corner case
        if (numCourses == 0) return new int[]{};

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegree = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            inDegree.put(i, 0);
        }

        for (int[] course : prerequisites) {
            graph.putIfAbsent(course[1], new HashSet<>());
            graph.get(course[1]).add(course[0]);
            inDegree.put(course[0], inDegree.get(course[0]) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (Integer key: inDegree.keySet()) {
            if (inDegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer cur = queue.poll();
            list.add(cur);
            if (graph.containsKey(cur)) {
                for (int next: graph.get(cur)) {
                    inDegree.put(next, inDegree.get(next) - 1);
                    if (inDegree.get(next) == 0) queue.offer(next);
                }
            }
        }
        if (list.size() != numCourses) return new int[] {};
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}
