package round2.oa.airbnb_shengqiu;/*
Assumption:
1. The input is a list of list, such as [[1, 2], [1,4], [3,4]...], represents 1 know 2 and 4, 3 know 4
2. The cost between two node are the square of diff.
3. return the min cost from one number to another number
4. if can not reach another number, we return -1

Approach: Dijkstra
So, in the high level, this is a graph problem that edges have positive weight, we can consider use Dijkstra Algorithm to
solve this problem.
In detail, for a standard dijkstra, we need a priority queue, a set to store the node that is done.
Also, we can have Map to record the current min cost for each node, thus we can reduce the element in our priority queue.

Time: O(E*logV)
in the worst case, since each node can only expand once, so the worst case is each edge in the graph are offered to
priority queue once.
that is E*logE, which is E*logV^2 which is 2*E*logV = O(E*logV)
Space: O(E)
in the worst case, the priority queue will has size of O(E)
==========================
Follow:
1. DFS or BFS, DFS and BFS is not good, because we will keep visiting or updating the same node,
but with Dijkstra, we will always pick the smallest path
 */

import java.util.*;

public class ATenWizard {
    public static void main(String[] args) {
        ATenWizard sol = new ATenWizard();
        int[][] wizards = new int[][] {
                {5},
                {3},
                {4, 7},
                {4},
                {8},
                {2},
                {8, 9},
                {9},
                {9},
                {1, 2, 3}
        };
        List<List<Integer>> wizardList = new ArrayList<>();
        for (int i = 0; i < wizards.length; i++) {
            wizardList.add(new ArrayList<>());
            for (int j = 0; j < wizards[i].length; j++) {
                wizardList.get(i).add(wizards[i][j]);
            }
        }
        System.out.println(sol.findMinCost(wizardList, 0, 9).path);
        System.out.println(sol.findMinCost(wizardList, 0, 9).cost);
    }

    public Result findMinCost(List<List<Integer>> wizards, int from, int to) {
        int[] minCost = new int[wizards.size()];  // the min cost for each wizard so far
        Arrays.fill(minCost, Integer.MAX_VALUE);
        minCost[from] = 0;
        Queue<Wizard> pq = new PriorityQueue<>((o1, o2) -> {
            return Integer.compare(o1.cost, o2.cost);
        });
        Set<Integer> closed = new HashSet<>();
        pq.offer(new Wizard(from, 0, null));
        while (!pq.isEmpty()) {
            Wizard curr = pq.poll();
            if (closed.contains(curr)) continue;
            closed.add(curr.id);
            if (curr.id == to) {
                return new Result(curr.cost, getPath(curr));
            }
            for (int next : wizards.get(curr.id)) {
                int cost = curr.cost + (next - curr.id) * (next - curr.id);
                if (cost >= minCost[next]) continue;
                minCost[next] = cost;
                pq.offer(new Wizard(next, cost, curr));
            }
        }
        return null;
    }

    private List<Integer> getPath(Wizard end) {
        List<Integer> list = new ArrayList<>();
        while (end != null) {
            list.add(end.id);
            end = end.from;
        }
        Collections.reverse(list);
        return list;
    }

    class Result {
        int cost;
        List<Integer> path;

        public Result(int cost, List<Integer> path) {
            this.cost = cost;
            this.path = path;
        }
    }
}

class Wizard {
    int id;
    int cost;
    Wizard from;

    public Wizard(int id, int cost, Wizard from) {
        this.id = id;
        this.cost = cost;
        this.from = from;
    }
}

// BFS
//    public int findMinCost(List<List<Integer>> list, int start, int end) {
//        // BFS with Queue
//        Queue<Wizard> queue = new LinkedList<>();
//        queue.offer(new Wizard(start, 0, null));
//        int res = Integer.MAX_VALUE;
//        Wizard wizard = null;
//        while (!queue.isEmpty()) {
//            Wizard cur = queue.poll();
//            if (cur.id == end) {
//                wizard = cur;
//                res = Math.min(res, cur.cost);
//            }
//            for (int nei : list.get(cur.id)) {
//                int cost = cur.cost + (nei - cur.id) * (nei - cur.id);
//                if (cost > res) continue;
//                queue.offer(new Wizard(nei, cost, cur));
//            }
//        }
//        System.out.println(printPath(wizard));
//        return res == Integer.MAX_VALUE ? -1 : res;
//    }
//
//    private void printPath(Wizard end) {
//        List<Integer> path = new ArrayList<>();
//        while (end != null) {
//            path.add(end.id);
//            end = end.from;
//        }
//        Collections.reverse(path);
//        System.out.println(path);
//    }