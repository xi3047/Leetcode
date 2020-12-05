package round1;

public class L217_checkUnique {
    public static boolean checkUnique (char[] chars) {
        if (chars == null) {
            throw new IllegalArgumentException();
        }

        int[] set = new int[8]; // 8 * (32) = 256
        for (char c: chars) {
            int row = c / 32;	// 计算出当前字母对应哪个int
            int col = c % 32;	// 计算出当前字母在int里32个bit的位置

            // 如果字母出现过的话，bit &出来应该是1
            if ((set[row] & (1 << col)) != 0) {
                return false;
            }
	    else {set[row] |= 1 << col;}  //如果没有过，在这个对应的bit上标记为1
        }
        return true;
    }

    public static boolean isUnique (char [] chars) {
        int checker = 0;

        for (char c : chars) {
            if ((checker & (1 << c)) != 0) {
                return false;
            }
            checker |= 1 << c;
        }
        return true;
    }



    public static void main(String[] args) {
        char [] set = {'a','b','c','d','$', '1','#', '!'};


        System.out.println(checkUnique(set));
        System.out.println(isUnique(set));


    }
}

