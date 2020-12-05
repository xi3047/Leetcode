package round1_misc.amazon;
/*
    @author: Xi Zhang
    @date:   2019-06-07
    @time:   00:38
 */

import org.junit.Test;

import java.util.*;

/**
 * Input: A reverted n-nary tree in the format of HashMap<Child, Parent>
 * Output: A map that counts the number of all descending children of every node
 *
 * Idea: 1. Traverse the original input hashmap to generate a parent-children hashmap
 *       2. Recursively call each unique Node to get the number of its children
 *       3. Use a count map to memoize nodes that are already computed
 *
 * Time complexity: O(n), where is n is the total number of nodes in the tree
 * Space: O(n)
 */
public class countChildrenNaryTree {
    public HashMap<String, Integer> countChildrenNaryTree(HashMap<String, String> rawTree) {
        HashMap<String, List<String>> processedTree = new HashMap<>();
        HashSet<String> uniqueNodes = new HashSet<>();
        for (Map.Entry<String, String> entry : rawTree.entrySet()) {
            String child = entry.getKey();
            String parent = entry.getValue();
            if (!processedTree.containsKey(parent)) {
                processedTree.put(parent, new ArrayList<>());
            }
            processedTree.get(parent).add(child);
            uniqueNodes.add(child);
            uniqueNodes.add(parent);
        }

        HashMap<String, Integer> countMap = new HashMap<>();

        for (String nodeID : uniqueNodes) {
            countNode(nodeID, processedTree, countMap);
        }
        return countMap;
    }

    private int countNode(String curNode, HashMap<String, List<String>> tree, HashMap<String, Integer> countMap ) {
        if (!tree.containsKey(curNode)) {
            countMap.put(curNode, 0);
            return 0;
        }
        // memoization get
        if (countMap.get(curNode) != null) {
            return countMap.get(curNode);
        }
        List<String> curChildren = tree.get(curNode);
        int count = 0;
        for (String children : curChildren) {
            count += countNode(children, tree, countMap) + 1;
        }
        // memoization put
        countMap.put(curNode, count);
        return count;
    }

    /**
     * 2
     */
    public HashMap<String, Integer> countChildToParentTree(HashMap<String, String> reversedTree) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Map.Entry<String,String> entry : reversedTree.entrySet()) {
            String child = entry.getKey();
            String parent = entry.getValue();
            if (!child.equals(parent)) {
                map.put(parent, map.getOrDefault(parent, 0) + 1);
            }
        }
        HashMap<String, Integer> res = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        for (String key : reversedTree.keySet()) {
            if (!map.containsKey(key)) {
                res.put(key, 0);
                queue.offer(key);
            }
        }
        while (!queue.isEmpty()) {
            String cur = queue.poll();
            String curParent = reversedTree.get(cur);
            if (!cur.equals(curParent)) {
                queue.offer(curParent);
                res.put(curParent, res.getOrDefault(curParent, 0) + 1);
            } else {
                res.put(curParent, res.get(curParent) + queue.size());
            }

        }
        return res;
    }

    @Test
    public void test () {
        HashMap<String, String> tree = new HashMap<>();
        tree.put("A", "A");
        tree.put("B", "A");
        tree.put("C", "A");
        tree.put("D", "B");
        tree.put("E", "B");
        tree.put("F", "C");
        HashMap<String, Integer> countMap = countChildToParentTree(tree);
        for (String nodeID : countMap.keySet()) {
            System.out.println(nodeID + " has " + countMap.get(nodeID)  + " children nodes.");
        }
    }
}
