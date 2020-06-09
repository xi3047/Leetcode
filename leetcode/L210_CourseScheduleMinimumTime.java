package leetcode;

import org.junit.Test;

import java.util.*;

public class L210_CourseScheduleMinimumTime {
    public int findOrderBFS(int numCourses, int[][] prerequisites) {
        // corner case

        // Initialize graph
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();

        // Initialize in-Degree Map with values
        for (int i = 0; i < numCourses; i++) {
            inDegreeMap.put(i, 0);
        }

        // Build graph and In-Degree Map
        for (int[] course: prerequisites) {
            graph.putIfAbsent(course[1], new HashSet<>());
            graph.get(course[1]).add(course[0]);
            inDegreeMap.put(course[0], inDegreeMap.getOrDefault(course[0], 0) + 1);
        }

        // Initialize a queue and put courses with 0 In-degree to it
        Queue<Integer> queue = new LinkedList<>();
        for (Integer key: inDegreeMap.keySet()) {
            if (inDegreeMap.get(key) == 0) {
                queue.offer(key);
            }
        }
        int number = 0;
        // use BFS to output a path
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                Integer curCourse = queue.poll();
                list.add(curCourse);
                if (graph.containsKey(curCourse)) {
                    for (int nextCourse: graph.get(curCourse)) {
                        inDegreeMap.put(nextCourse, inDegreeMap.get(nextCourse) - 1);
                        if (inDegreeMap.get(nextCourse) == 0) queue.offer(nextCourse);
                    }
                }
            }
            number++;
        }

        // If there are disjoint set(aka. independent course) return empty array
        if (list.size() != numCourses)  {
            System.out.println("Not possible to finish all course");
        } else {
            System.out.println("To finish all the course take in order of :" + list);
        }

      return number;
    }

    @Test
    public void test() {
        int[][] courses = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        System.out.println(findOrderBFS(4, courses));
    }
}
