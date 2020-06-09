package leetcode;

import java.util.HashSet;
import java.util.Set;

public class L929_UniqueEmail {
    public int numUniqueEmails(String[] emails) {
        Set<String> seen = new HashSet();
        for (String email: emails) {
            int i = email.indexOf('@');
            String local = email.substring(0, i);
            String rest = email.substring(i);
            if (local.contains("+")) {
                local = local.substring(0, local.indexOf('+'));
            }
            local = local.replaceAll("\\.","");
            seen.add(local + rest);
        }

        return seen.size();

    }
}
