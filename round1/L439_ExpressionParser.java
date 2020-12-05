package round1;
/*
    @author: Xi Zhang
    @date:   2019-02-20
    @time:   20:34


 */
import java.util.Stack;

public class L439_ExpressionParser {
    public static String parseTernary(String s) {
        Stack<String> stack = new Stack<>();
        int i = s.length() - 1;
        while (i >= 0) {
            Character ch = s.charAt(i);
            if (ch == ':') {
                i--;
            } else if (ch == '?') {
                String t = stack.pop();
                String f = stack.pop();
                Character condition = s.charAt(--i);
                switch (condition) {
                    case 'T': stack.push(t); break;
                    case 'F': stack.push(f); break;
                    default: throw new IllegalArgumentException("invalid argument");
                }
                i--;
            } else {
                StringBuilder sb = new StringBuilder();
                while (i >= 0 && s.charAt(i) != ':' && s.charAt(i) != '?') {
                    sb.append(s.charAt(i--));
                }
                stack.push(sb.reverse().toString());
            }


        }
        return stack.pop();
    }

    public static void main(String[] args) {
        System.out.printf(parseTernary("T?T?F:5:3"));
    }
}
