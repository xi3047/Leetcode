package round4.string;

import java.rmi.server.ExportException;

public class L65_ValidNumber {
    /*
    Meta followup : 00 is not a valid number! Similarly 01 and 1e01 is not a valid number.
    Basically any number starting with 0 is not a valid number. But 1.001 is a valid number
     */

    /**
     * Rules:
     * 1. + or - are allowed at start or after E
     * 2. . allowed once, not after E
     * 3. e or E allowed once, must follow a number
     * 4. digits at least one before and after . required before or after e
     */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean dotSeen = false;
        boolean expSeen = false;
        boolean digitSeen = false;

        if (s.length() > 1 && s.charAt(0) == '0') {
            char second = s.charAt(1);
            if (Character.isDigit(second) && second != '.' && second != 'e') {
                return false;
            }
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                digitSeen = true;
            } else if (c == '+' || c == '-') {
                // sign rule
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else if (c == '.') { // only one dot, not after E
                if (dotSeen || expSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (c == 'e' || c == 'E') {
                if (expSeen || !digitSeen) {
                    return false;
                }
                expSeen = true;
                digitSeen = false; // must have digits after e
            } else {
                return false;
            }
        }

        return digitSeen;
    }
}
