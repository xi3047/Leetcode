package round1_misc.OOD.amazon;

import round1.TwoDIntArrayToList;

import java.util.*;

public class getMinimumCostRepair {


    public static int getMinimumCostRepair(int numTotalAvailableCities, int numTotalAvailableRoads, List<List<Integer>> roadsAvaliable, int numRoadsToBeRepaired, List<List<Integer>> costRoadsToBeRepaired) {
        // Initialize
        Set<List<Integer>> brokenRoadsSet = new HashSet<>();
        Queue<Edge> edges = new PriorityQueue<>();

        for (List<Integer> brokenRoad : costRoadsToBeRepaired) {

            brokenRoadsSet.add(brokenRoad.subList(0, 2));

            Node start = new Node(brokenRoad.get(0), 1);
            start.parent = start;
            Node end = new Node(brokenRoad.get(1), 1);
            end.parent = end;
            edges.offer(new Edge(start, end, brokenRoad.get(2)));
        }

        for (List<Integer> road : roadsAvaliable) {
            if (!brokenRoadsSet.contains(road)) {
                Node start = new Node(road.get(0), 1);
                start.parent = start;
                Node end = new Node(road.get(1), 1);
                end.parent = end;
                //edges.offer(new Edge(start, end, 0)); // 解决 无解case
                union(start, end);
            }
        }

        int minCost = 0;
        int count = 1;
        for (int i = 0; i < edges.size(); i++) {
            if (count == numTotalAvailableCities - 1) {
                break;
            }
            Edge edge = edges.poll();
            int edgeCost = edge.cost;
            Node start = edge.start;
            Node end = edge.end;
            if (!find(start, end)) {
                minCost += edgeCost;
                union(start, end);
            }
            count++;
        }
        return minCost;
    }

    public static boolean find(Node p, Node q) {
        return getRoot(p) == getRoot(q);
    }

    public static void union(Node p, Node q) {
        Node rootP = getRoot(p);
        Node rootQ = getRoot(q);
        if (rootP.size < rootQ.size) {
            rootP.parent = rootQ;        // TODO：这里改了
            rootQ.size += rootP.size;
        } else {
            rootQ.parent = rootP;
            rootP.size += rootQ.size;
        }
    }

    private static Node getRoot(Node node) {
        Node cur = node;
        while (cur.parent != cur) {
            cur.parent = cur.parent.parent;   // TODO: 总觉得怪怪的！这里跳的原因和时间复杂度上的关系再看一下
            cur = cur.parent;
        }
        node.parent = cur;
        return cur;
    }

    static class Node {
        int cityID;
        int size;
        Node parent;

        public Node(int cityID, int size) {
            this.cityID = cityID;
            this.size = size;
            parent = null;
        }
    }

    static class Edge implements Comparable<Edge> {
        Node start;
        Node end;
        int cost;

        public Edge(Node start, Node end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) {
        int numTotalAvailableCities = 5;
        int numTotalAvailableRoads = 5;
        int numRoadsToBeRepaired = 3;
        int[][] roads = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}};
        List<List<Integer>> roadsAvaliable = TwoDIntArrayToList.convert2DIntArrayToList(roads);
        int[][] costs = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
        List<List<Integer>> costRoadsToBeRepaired = TwoDIntArrayToList.convert2DIntArrayToList(costs);
        System.out.println(getMinimumCostRepair(numTotalAvailableCities, numTotalAvailableRoads, roadsAvaliable, numRoadsToBeRepaired, costRoadsToBeRepaired));
    }
}
