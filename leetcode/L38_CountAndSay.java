package leetcode;

/*
    @author: Xi Zhang
    @date:   2019-03-09
    @time:   18:35

    For example:

If you have "1", the next line is "11".
If you have "11", the next line is "21".
If you have "111", the next line is "31".
If you have "1111", the next line is "41".
See the basic pattern? Just do that for every set of repeating characters on the previous line.
Here's what it looks like with more than one sequence:

If you have "11222", the next line is "2132" ("11" becomes "21" and "222" becomes "32").
If you have "112225", the next line is "213215" ("11" becomes "21", "222" becomes "32", and "5" becomes "15").
 */
public class L38_CountAndSay {
    public String countAndSay(int n) {
        if (n <= 0) return "";
        String str = "1";
        for (int i = 1; i < n; i++) {
            int count = 0;
            char prev = '.';
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < str.length(); idx++) {
                if (str.charAt(idx) == prev || idx == 0 ) {
                    count++;
                } else {
                    sb.append(count + Character.toString(prev));
                    count = 1;
                }
                prev = str.charAt(idx);
            }
            sb.append(count + Character.toString(prev));
            str = sb.toString();
        }
        return str;
    }
}
