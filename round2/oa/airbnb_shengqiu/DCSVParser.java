package round2.oa.airbnb_shengqiu;

/*
Assumption:
1. input is a string, return the parsed input
2. remove " " between word, replace , with |
3. if nested "", just remove the most outer ""

Approach:
The idea is we need to distinguish most outer quote with nested quote. This can be done by maintain a state.
If we not in a quote, we just append character and replace , with |, if we reach a ", we mark the state to
indicate we are in quote. If we then reach another quote, we check if this is a back quote or a nested quote,
if is a nested quote, we need append, otherwise, ignore.

Time: O(n) where n is the length of string
Space: O(n) for stringbuilder
 */
public class DCSVParser {
    public static void main(String[] args) {
        DCSVParser sol = new DCSVParser();
        System.out.println(sol.parseCSV("John,Smith,john.smith@gmail.com,Los Angeles,1"));
        System.out.println(sol.parseCSV("Jane,Roberts,janer@msn.com,\"San Francisco, CA\",0"));
        System.out.println(sol.parseCSV("\"Alexandra \"\"Alex\"\"\",Menendez,alex.menendez@gmail.com,Miami,1 \"\"\"Alexandra Alex\"\"\""));
    }

    public String parseCSV(String str) {
        StringBuilder sb = new StringBuilder();
        boolean inQuote = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (inQuote) {
                if (c == '\"') { // is a inner quote
                    if (i + 1 < str.length() && str.charAt(i + 1) == '\"') {
                        sb.append('\"');
                    } else { // is a end quote
                        inQuote = false;
                    }
                } else {
                    sb.append(c);
                }
            } else {
                if (c == '\"') { // a start quote
                    inQuote = true;
                } else if (c == ',') {
                    sb.append('|');
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
