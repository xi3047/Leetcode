package round4.bdfs;

import java.util.*;

public class L210_CourseScheduleII {
    enum Status { Initial, Processing, Done }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Status[] status = new Status[numCourses];
        Arrays.fill(status, Status.Initial);

        // 1) Build graph edges: prereq → courses
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            int course = p[0], prereq = p[1];
            graph.get(prereq).add(course);
        }

        // 2) Run DFS from every course
        for (int i = 0; i < numCourses; i++) {
            if (status[i] == Status.Initial) {
                if (dfs(i, graph, status, res)) {
                    return new int[0];  // cycle detected
                }
            }
        }

        // 3) 'res' is reverse topological order—reverse it
        Collections.reverse(res);

        // 4) Convert to int[]
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }

    private boolean dfs(int cur, Map<Integer, List<Integer>> graph,
                        Status[] status, List<Integer> res) {
        if (status[cur] == Status.Done) return false;
        status[cur] = Status.Processing;

        for (int next : graph.get(cur)) {
            if (status[next] == Status.Processing) {
                return true;  // cycle found
            }
            if (dfs(next, graph, status, res)) return true;
        }
        status[cur] = Status.Done;
        res.add(cur);  // add to result after children
        return false;
    }

    public static void main(String[] args) {
        L210_CourseScheduleII sol = new L210_CourseScheduleII();
        int[][] courses = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};
        int[] res  = sol.findOrder(4, courses);
        for (int p : res) {
            System.out.println(p);
        }
    }
}
