package round2.oa.karat;

import org.junit.Test;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 1/25/2021 4:45 PM
 * @topic round2.oa.karat
 * @link
 * @description
 * Suppose we have some input data describing a graph of relationships between parents and children over
 * multiple generations.
 * The data is formatted as a list of (parent, child) pairs, where each individual is assigned a unique integer identifier.
 */
public class Ancestor {
    /**
     * Question 1:
     * Write a function that takes this data as input and returns two collections: one containing all individuals
     * with zero known parents, and one containing all individuals with exactly one known parent
     */
    public List<List<Integer>> zeroAndOneParent(int[][] parentPairs) {
        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, Set<Integer>> parentMap = new HashMap<>();
        for (int [] pair :parentPairs) {
            parentMap.putIfAbsent(pair[1], new HashSet<>());
            parentMap.putIfAbsent(pair[0], new HashSet<>());
            parentMap.get(pair[1]).add(pair[0]);
        }
        List<Integer> zeroParent = new ArrayList<>();
        List<Integer> oneParent = new ArrayList<>();
        for (int node : parentMap.keySet()) {
            if (parentMap.get(node).size() == 0) {
                zeroParent.add(node);
            } else if (parentMap.get(node).size() == 1) {
                oneParent.add(node);
            }
        }
        res.add(zeroParent);
        res.add(oneParent);
        return res;
    }

    /**
     * Question 2:  Write a function that takes the graph, as well as two of the individuals in our dataset, as its
     * inputs and returns true if and only if they share at least one ancestor.
     */
    public boolean hasCommonAncestor(int[][] parentPairs, int a, int b) {
        Map<Integer, Set<Integer>> parentMap = new HashMap<>();
        for (int [] pair :parentPairs) {
            parentMap.putIfAbsent(pair[1], new HashSet<>());
            parentMap.putIfAbsent(pair[0], new HashSet<>());
            parentMap.get(pair[1]).add(pair[0]);
        }
        Set<Integer> set = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        queue.offer(b);
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (parentMap.get(cur).size() > 0) {
                for (int parent : parentMap.get(cur)) {
                    if (!set.add(parent)) return true;
                    queue.offer(parent);
                }
            }
        }
        return false;
    }

    /**
     * Question 3: Write a function that, for a given individual in our dataset, returns their earliest known ancestor
     * -- the one at the farthest distance from the input individual. If there is more than one ancestor tied for
     * "earliest", return any one of them. If the input individual has no parents, the function should return null (or -1).
     */
    public int findEarliestAncestor(int[][] parentPairs, int a) {
        Map<Integer, Set<Integer>> parentMap = new HashMap<>();
        for (int [] pair :parentPairs) {
            parentMap.putIfAbsent(pair[1], new HashSet<>());
            parentMap.putIfAbsent(pair[0], new HashSet<>());
            parentMap.get(pair[1]).add(pair[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(a);
        int earliest = -1;
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (parentMap.get(cur).size() > 0) {
                for (int parent : parentMap.get(cur)) {
                    queue.offer(parent);
                    earliest = parent;
                }
            }
        }
        return earliest;
    }


    @Test
    public void test1(){
        int[][] pairs = {{1,3}, {2,3}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {4,9}, {9, 11}};
        System.out.println(zeroAndOneParent(pairs));
    }

    @Test
    public void test2(){
        int[][] pairs = {{1, 3}, {2, 3}, {3, 6}, {5, 6}, {5, 7}, {4, 5},{4, 8},
                {4, 9}, {9, 11}, {14, 4}, {13, 12}, {12, 9}};
        System.out.println(hasCommonAncestor(pairs, 3, 8));
        System.out.println(hasCommonAncestor(pairs, 5, 8));
        System.out.println(hasCommonAncestor(pairs, 6, 8));
        System.out.println(hasCommonAncestor(pairs, 6, 9));
        System.out.println(hasCommonAncestor(pairs, 1, 3));
        System.out.println(hasCommonAncestor(pairs, 7, 11));
        System.out.println(hasCommonAncestor(pairs, 6, 5));
        System.out.println(hasCommonAncestor(pairs, 5, 6));
    }

    @Test
    public void test3() {
        int[][] pairs = {{2,3}, {3,15}, {3,6}, {5,6}, {5,7}, {4,5}, {4,8}, {4,9}, {9, 11}, {14,2}};
        System.out.println(findEarliestAncestor(pairs, 8));
        System.out.println(findEarliestAncestor(pairs, 7));
        System.out.println(findEarliestAncestor(pairs, 6));
        System.out.println(findEarliestAncestor(pairs, 15));
        System.out.println(findEarliestAncestor(pairs, 14));


    }
}
