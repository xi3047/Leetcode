package round4.string;

import java.util.ArrayList;
import java.util.List;

public class L271_EncodeDecodeStrings {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str :  strs) {
            sb.append(str.length()).append('#').append(str);
        }
        return sb.toString();
    }

    public List<String> decode (String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            int length = Integer.parseInt(s.substring(i, j));
            res.add(s.substring(j + 1, j + 1 + length));
            i = j + 1 + length;
        }
        return res;
    }
}
