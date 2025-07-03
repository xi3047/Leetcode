package round4.otherDataStructure;

import round1.TreeNode;

import java.util.*;

public class L314_BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) return res;
        //column to list of treeNode map
        Map<Integer, List<Integer>> map = new HashMap<>();
        //queue for traversal
        Queue<Node> queue = new LinkedList<>();
        // column map
        queue.offer(new Node(root, 0));
        int min = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            TreeNode cur = node.treeNode;
            int curColumn = node.col;
            map.putIfAbsent(curColumn, new ArrayList<>());
            map.get(curColumn).add(cur.val);
            if (cur.left != null) {
                queue.offer(new Node(cur.left, curColumn - 1));
                min = Math.min(min, curColumn - 1);
            }
            if (cur.right != null) {
                queue.offer(new Node(cur.right, curColumn + 1));
            }
        }
        while (map.containsKey(min)) {
            res.add(map.get(min++));
        }
        return res;
    }

    class Node {
        TreeNode treeNode;
        int col;

        Node(TreeNode treeNode, int col) {
            this.treeNode = treeNode;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal2(TreeNode root) {
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> cols = new LinkedList<>();

        q.add(root);
        cols.add(0);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            int col = cols.poll();

            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>());
            }
            map.get(col).add(node.val);

            if (node.left != null) {
                q.add(node.left);
                cols.add(col - 1);
            }

            if (node.right != null) {
                q.add(node.right);
                cols.add(col + 1);
            }
        }

        return new ArrayList(map.values());
    }


    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        Set<Character> signSet = Set.of('+', '-', '*', '/');
        for (int i = 0; i < s.toCharArray().length; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            }
            if (signSet.contains(c) || i == s.length() - 1) {
                if (sign == '+') {
                    stack.push(num);
                } else if (sign == '-') {
                    stack.push(-num);
                } else if (sign == '*') {
                    stack.push(stack.pop() * num);
                } else if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                num = 0;
                sign = c;
            }

        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
