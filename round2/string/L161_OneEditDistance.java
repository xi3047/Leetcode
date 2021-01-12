package round2.string;

public class L161_OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int n1 = s.length(), n2 = t.length();
        if (n1 > n2)  return isOneEditDistance(t, s);
        for (int i = 0; i < n1; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (n1 == n2) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return s.substring(i).equals(t.substring(i + 1));
                }
            }
        }
        return (n1 + 1 == n2);
    }
}

