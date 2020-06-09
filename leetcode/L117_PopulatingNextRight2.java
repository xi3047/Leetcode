package leetcode;

/*
    @author: Xi Zhang
    @date:   2/2/19
    @time:   11:07 PM
 */
public class L117_PopulatingNextRight2 {
    public void connect(TreeLinkNode root) {
        TreeLinkNode head = null;
        TreeLinkNode prev = null;
        TreeLinkNode cur = root;

        while (cur != null) {
            while (cur != null) {
                //left
                if (cur.left != null) {
                    if (prev == null) {
                        head = cur.left;
                    } else {
                        prev.next = cur.left;
                    }
                    prev = cur.left;
                }
                //right
                if (cur.right != null) {
                    if (prev == null) {
                        head = cur.right;
                    } else {
                        prev.next = cur.right;
                    }
                    prev = cur.right;
                }
                cur = cur.next;
            }
            //move on to next level
            cur = head;
            head = null;
            prev = null;
        }
    }

}
