package round1;

import org.junit.Test;

import java.util.*;

public class L314_verticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        class VNode{
            TreeNode node;
            int index;
        public VNode(TreeNode node, int index) {
                this.node = node;
                this.index = index;
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        HashMap<Integer, List<Integer>> map =new HashMap<>();
        Queue<VNode> queue = new LinkedList<>();
        queue.offer(new VNode(root, 0));
        while (!queue.isEmpty()) {
            VNode cur = queue.poll();
            TreeNode curNode = cur.node;
            int index = cur.index;

            //add to map
            List<Integer> list = map.getOrDefault(index, new ArrayList<>());
            list.add(curNode.val);
            map.put(index, list);
            // go left
            if (curNode.left != null) {
                queue.offer(new VNode(curNode.left, index -1));
            }
            // go right
            if (curNode.right != null) {
                queue.offer(new VNode(curNode.right, index + 1));
            }
        }

        List<Integer> indices = new ArrayList<>();
        indices.addAll(map.keySet());
        Collections.sort(indices);
        for (int i : indices) {
            List<Integer> curList = map.get(i);
            res.add(curList);
        }
        return res;
    }

    // dfs helper,  order isn't correct
    private void helper(TreeNode root, int index, HashMap<Integer, List<Integer>> map) {
        if (root == null) return;
        List<Integer> list = map.getOrDefault(index, new ArrayList<>());
        list.add( root.val);
        map.put(index, list);
        helper(root.left, index - 1, map);
        helper(root.right, index + 1, map);

    }

    @Test
    public void test() {
        TreeNode root = TreeNode.createTree(new Integer[]{3,9,8,4,0,1,7,null,null,null,2,5});
        TreeNode.visualize(root);
        System.out.println(verticalOrder(root));
    }
}
