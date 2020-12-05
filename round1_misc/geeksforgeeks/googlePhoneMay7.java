package round1_misc.geeksforgeeks;

import org.junit.Test;

public class googlePhoneMay7 {
    public boolean canConcat(String a, String b) {
        return false;
    }

    public int minConcat(String a, String b) {
        int count = 0;

        int i = 0;
        while ( i < a.length()) {
            StringBuilder sb = new StringBuilder();
            sb.append(a.charAt(i));

            boolean flag = false;
            int j = i;
            while (b.indexOf(sb.toString()) != -1 && j < a.length() - 1) {
                j++;
                sb.append(a.charAt(j));
                if (j - i > 1) {
                    flag = true;
                }
            }
            count++;
            if (flag) {
                i = j + 1;
                continue;
            }
            i++;
        }
        return count;
    }

    @Test
    public void test() {
        String a = "ZAZA";
        String b = "BAZ";
        System.out.println(minConcat(a, b));
    }
}
