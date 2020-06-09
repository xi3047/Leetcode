package leetcode;

import org.junit.Test;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-05-04
    @time:   16:35
    There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take.
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
 */

/**
 * DFS using HashMap graph and StatusMap to check isCycle
 */
public class L207_CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        HashMap<Integer, Status> statusHashMap = new HashMap<>();

        for (int[] course : prerequisites) {
            if (!graph.containsKey(course[0])) {
                graph.put(course[0], new ArrayList<>());
            }
            graph.get(course[0]).add(course[1]);
        }
        for (int key: graph.keySet()) {
            if (containsCycle(graph, key, statusHashMap)) return false;
        }
        return true;
    }

    private boolean containsCycle(HashMap<Integer, List<Integer>> graph, int cur, HashMap<Integer, Status> statusHashMap) {
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
                if (containsCycle(graph, next, statusHashMap)) return true;
            }
        };
        statusHashMap.put(cur, Status.Done);
        return false;
    }

    enum Status {
        Initial, Processing, Done;
    }

    // check cycle using HashSet, time limit exceeded 312 ms.

    private boolean containsCycleSet(HashMap<Integer, List<Integer>> graph, int cur, HashSet<Integer> visited) {
        if (visited.contains(cur)) {
            return true;
        }
        visited.add(cur);
        if (graph.get(cur) != null) {
            for (int next : graph.get(cur)) {
                if (containsCycleSet(graph, next, visited)) return true;
            }
        }
        visited.remove(cur);
        return false;
    }

    @Test
    public void test(){
        int[][] preq = {{2,0}, {1,0}, {3,1}, {3,2}, {1,3} };
        System.out.println(canFinish(4,preq));
    }





}
