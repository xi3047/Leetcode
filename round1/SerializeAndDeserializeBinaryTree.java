package round1;

import org.junit.Assert;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * *  *  *  *  *  *  *
 * *  *  *  *  *  *  *
 * *  *  *  *  *  *  *
 * created by song at 1/30/19
 * Leetcode 297 Hard, high freq
 * *  *  *  *  *  *  *
 * *  *  *  *  *  *  *
 * *  *  *  *  *  *  *
 * *
 **/
public class SerializeAndDeserializeBinaryTree {
    private static final String delimiter = ",";
    private static final TreeNode NULLNODE = new TreeNode(Integer.MIN_VALUE);

    //this solution beats all but one Memory Limit Exceeded case..
    public static String serialize(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        int nodesToAdd = 1;

        while (!queue.isEmpty()) {
            int nullNodesCount = 0;
            for (int i = 0; i < nodesToAdd; i++) {
                TreeNode curr = queue.poll();

                if (curr.val == NULLNODE.val) {
                    nullNodesCount += 2;
                    queue.offer(NULLNODE);
                    queue.offer(NULLNODE);
                    sb.append("null").append(delimiter);
                    continue;
                } else {
                    sb.append(curr.val).append(delimiter);
                }
                if (curr.left == null) {
                    queue.offer(NULLNODE);
                    nullNodesCount++;
                } else {
                    queue.offer(curr.left);
                }

                if (curr.right == null) {
                    queue.offer(NULLNODE);
                    nullNodesCount++;
                } else {
                    queue.offer(curr.right);
                }
            }
            nodesToAdd *= 2;
            if (nullNodesCount == nodesToAdd) {
                break;
            }
        }
        sb.deleteCharAt(sb.length() - 1);//leetcode style
        return "[" + sb.toString() + "]";
    }

    //my format: "1 2 3 null null 4 5"
    //i.e.      1
    //      2       3
    //null  null  4  5
    //
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() < 1) return null;
        //clean up, remove "[" and "]" at both ends
        StringBuilder sb = new StringBuilder(data);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);

        String[] nodes = sb.toString().split(delimiter);

        if (nodes.length < 1) return null;

        TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int index = 0;

        //this deserializer has some problems oh man

        while (!queue.isEmpty()) {
            if (index >= nodes.length - 1) {
                break;
            }
            TreeNode curr = queue.poll();

            index++;
            if (nodes[index].equals("null")) {
                queue.offer(NULLNODE);
                //curr.left = new TreeNode(NULLNODE.val);
            } else {
                curr.left = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(curr.left);
            }
            index++;
            if (nodes[index].equals("null")) {
                queue.offer(NULLNODE);
                //curr.right = new TreeNode(NULLNODE.val);
            } else {
                curr.right = new TreeNode(Integer.parseInt(nodes[index]));
                queue.offer(curr.right);
            }
        }
        return root;
    }

    @Test
    public void testSerialize1() {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        root.left = two;
        two.left = three;

        String data = serialize(root);
        Assert.assertEquals("[1,2,null,3,null,null,null]", data);
        TreeNode.visualize(deserialize(data));
    }

    @Test
    public void testDeserialize1() {
        String data = "[1,2,null,3,null,null,null]";
        TreeNode root = deserialize(data);
        TreeNode.visualize(root);
        Assert.assertEquals(1, root.val);
        Assert.assertEquals(2, root.left.val);
        Assert.assertEquals(3, root.left.left.val);
        Assert.assertNull(root.right);
    }

    @Test
    public void testSerialize2() {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        root.left = two;
        root.right = three;
        three.left = four;
        three.right = five;
        four.left = six;
        TreeNode.visualize(root);
        Assert.assertEquals("[1,2,3,null,null,4,5,null,null,null,null,6,null,null,null]",
                serialize(root));
    }

    @Test
    public void testDeserialize2() {
        String data = "[1,2,3,null,null,4,5,null,null,null,null,6,null,null,null]";
        TreeNode root = deserialize(data);
        TreeNode.visualize(root);

        Assert.assertEquals(data, serialize(root));
    }

    @Test
    public void testSerializeLeetcode() {
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        root.left = two;
        two.left = three;
        three.left = four;
        four.left = five;
        //[1,
        // 2,null,
        // 3,null,null,null,
        // 4,null,null,null,null,null,null,null,
        // 5,null,null,null,null,null,null,null,
        // null,null,null,null,null,null,null,null]
        System.out.println(serialize(root));
    }

    @Test
    public void testDeserializeLeetcodeStyle() {
        //[1,2,null,3,null,4,null,5];
        //[
        TreeNode root = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        root.left = two;
        two.left = three;
        three.left = four;
        four.left = five;

        String data = serialize(root);
        System.out.println(data);

        TreeNode goal = deserialize(data);
        TreeNode.visualize(goal);
        Assert.assertEquals(1, goal.val);
        Assert.assertEquals(2, goal.left.val);
        Assert.assertEquals(3, goal.left.left.val);
        Assert.assertEquals(4, goal.left.left.left.val);
        Assert.assertEquals(5, goal.left.left.left.left.val);


    }
}