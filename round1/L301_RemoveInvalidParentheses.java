package round1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class L301_RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) return result;

        //check how many '(' and ')' we need to remove
        int rmL = 0;
        int rmR = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') rmL++;
            if (c == ')') {
                if (rmL == 0) rmR++;
                else rmL--;
            }
        }
        System.out.println("rmL: " + rmL + ", rmR: " + rmR + ".");
        dfs(result, s.toCharArray(), 0, rmL, rmR, 0, new StringBuilder());
        return result;
    }

    private void dfs(List<String> result, char[] s, int i, int rmL, int rmR,
                     int open, StringBuilder sb) {
        if (i == s.length && rmL == 0 && rmR == 0 && open == 0) {
            result.add(sb.toString());
            return;
        }

        if (i >= s.length || rmL < 0 || rmR < 0 || open < 0) return;

        //cache length before remove/keep, prepare for backtracking
        int length = sb.length();
        //left '('
        if (s[i] == '(') {
            //remove '('
            dfs(result, s, i + 1, rmL - 1, rmR, open, sb);

            //keep '('
            int rep = 1;
            while (i + rep < s.length && s[i + rep] == '(') {
                rep++;
            }
            sb.append(s, i, rep);
            dfs(result, s, i + rep, rmL, rmR, open + rep, sb);
            sb.setLength(length);
        }
        //right ')'
        else if (s[i] == ')') {
            //remove ')'
            dfs(result, s, i + 1, rmL, rmR - 1, open, sb);

            //keep ')'
            int rep = 1;
            while (i + rep < s.length && s[i + rep] == ')') {
                rep++;
            }
            sb.append(s, i, rep);
            dfs(result, s, i + rep, rmL, rmR, open - rep, sb);
            sb.setLength(length);
        }

//        else {
//            sb.append(s[i]);
//            dfs(result, s, i + 1, rmL, rmR, open, sb);
//            sb.setLength(length);
//        }
    }


    @Test
    public void tae() {
        String s = "()())()";
        System.out.println(removeInvalidParentheses(s));
    }
}
