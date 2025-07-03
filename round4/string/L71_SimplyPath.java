package round4.string;

import java.util.Arrays;
import java.util.Stack;

public class L71_SimplyPath {
    /*
       . skip
       "" skip
       .. pop
        push rest
     */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] parts = path.split("/");
        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) continue;
            else if (part.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else {
                stack.push(part);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String part : stack) {
            sb.append("/").append(part);
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }

    /**
     * Variant
     * Given a current working directory, and a cd path, output the changed simplified path after applying cd
     * cwd = "/a/b/c", cd = "/d/./e" output: "/d/e"
     * cwd = "", cd = "/d./e"  output: "/d/e"
     * cwd = "/a/b/c" cd = "", output: "/a/b/c"
     * cwd = "/a/b" cd = ".//c/../../d/f" output: "/a/d/f"
     */
    public String cd(String cwd, String cd) {
        if (cd.isEmpty()) return cwd;
        String[] parts = cwd.split("/");
        Stack<String> stack = new Stack<>();
        for (String part : parts) {
            if (part.isEmpty()) continue;
            stack.push(part);
        }
        String[] cds = cd.split("/");
        for (String part : cds) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(part);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String part: stack) {
            sb.append("/").append(part);
        }
        return sb.isEmpty() ? "/" : sb.toString();
    }
}
