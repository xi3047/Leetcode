package leetcode;

public class L331_VerifyPreorderBT {
    public boolean isValidSerialization(String preorder) {
        String[] ss = preorder.split(",");
        int delta = 1; // delta = No. of nodes - No. of # + 1
        for (String str : ss) {
            if (delta <= 0) return false;
            if (str.equals("#")) {
                delta--;
            } else { // internal node
                delta++;
            }
        }
        return delta == 0;
    }

}
