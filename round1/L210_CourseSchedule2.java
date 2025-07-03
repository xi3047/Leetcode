package round1;

import org.junit.Test;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-05-06
    @time:   18:34

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
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */
public class L210_CourseSchedule2 {
    /**
     * DFS Solution
     * Time: 10 ms
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Status> statusHashMap = new HashMap<>();
        HashMap<Integer, Boolean> degree = new HashMap<>();
        for (int[] course : prerequisites) {
            if (!graph.containsKey(course[0])) {
                graph.put(course[0], new ArrayList<>());
            }
            degree.put(course[1], true);
            graph.get(course[0]).add(course[1]);
        }
        for (int start = 0; start < numCourses; start++) {
            if (degree.get(start) == null) //如果当前的入度为0，就从他开始
            {
                containsCycle(graph, start, statusHashMap, res);
            }
        }
        if (res.size() != numCourses) return new int[]{};

        int[] arr = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }

    private boolean containsCycle(HashMap<Integer, List<Integer>> graph, int cur, HashMap<Integer, Status> statusHashMap, List<Integer> res) {
        Status status = statusHashMap.get(cur);
        if (status == Status.Processing) {
            return true;
        }
        if (status == Status.Done) {
            return false;
        }
        statusHashMap.put(cur, Status.Processing);
        if (graph.get(cur) != null) {
            for (int next : graph.get(cur)) {
                if (containsCycle(graph, next, statusHashMap, res)) return true;
            }
        }
        statusHashMap.put(cur, Status.Done);
        res.add(cur);
        return false;
    }

    enum Status {
        Initial, Processing, Done;
    }

    /**
     * BFS Solution
     * Note: cannot check if the graph contains cycle or not
     * Time: 12 ms
     */
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        // corner case
        if (numCourses == 0 ) return new int[]{};

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
        // use BFS to output a path
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            Integer curCourse = queue.poll();
            list.add(curCourse);
            if (graph.containsKey(curCourse)) {
                for (int nextCourse: graph.get(curCourse)) {
                    inDegreeMap.put(nextCourse, inDegreeMap.get(nextCourse) - 1);
                    if (inDegreeMap.get(nextCourse) == 0) queue.offer(nextCourse);
                }
            }
        }

        // If there are disjoint set(aka. independent course) return empty array
        if (list.size() != numCourses) return new int[]{};

        // put the result from list to the int array
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    @Test
    public void test() {

        //int[][] courses = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[][] courses = {{1, 0}};
        int[] res = findOrder(4, courses);
        for (int i : res) {
            System.out.print(i +" ");
        }
    }

    @Test
    public void test2() {
        int[][] courses = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int [] res = findOrderBFS(2, courses);
        for (int i : res) {
            System.out.print(i + ", ");
        }
    }
}
