package round4.otherDataStructure;

import java.util.*;

public class privilege {
    /**
     *
     */
    public static List<List<Character>> find(int n, char[][] allowedList, char[][] denyList, int[][] grants) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> parentMap = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indegree.put(i, 0);
        }
        for (int[] grant : grants) {
            int from = grant[0];
            int to = grant[1];
            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(to);
            parentMap.put(to, from);
            indegree.put(to, indegree.get(to) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree.get(i) == 0) {
                queue.offer(i);
            }
        }
        char[][] result = new char[n][];
        while (!queue.isEmpty()) {
            int role = queue.poll();
            List<Character> list = new ArrayList<>();
            char[] allowed = allowedList[role];
            char[] deny = denyList[role];
            Integer parent = parentMap.get(role);
            if (parent != null) {
                char[] parentList = result[parent];
                for (char c : parentList) {
                    list.add(c);
                }
            }
            for (char a : allowed) {
                if (list.contains(a)) continue;
                list.add(a);
            }
            for (char d : deny) {
                if (list.contains(d)) {
                    list.remove((Character) d);
                }
            }
            result[role] = new char[list.size()];
            for (int i = 0; i < list.size(); i++) {
                result[role][i] = list.get(i);
            }

            for (int next : graph.getOrDefault(role, new ArrayList<>())) {
                indegree.put(next, indegree.get(next) - 1);
                if (indegree.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        List<List<Character>> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            char[] privileges = result[i];
            List<Character> list = new ArrayList<>();
            for (char p : privileges) {
                list.add(p);
            }
            ans.add(list);
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] allowedList = {{'A'} , {'B'}, {'C'}};
        char[][] denyList = {{}, {'A'}, {}};
        int[][] grants = {{0, 1}, {1, 2}};
        System.out.println(find(3, allowedList, denyList, grants));
    }




}
