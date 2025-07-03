package round4.string;

public class L408_ValidWordAbbreviation {
    public boolean validWordAbbreviation(String word, String abbr) {
        int m = word.length(), n = abbr.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (Character.isDigit(abbr.charAt(j))) {
                int num = 0;
                if (abbr.charAt(j) == '0') return false;
                while (j < n && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + abbr.charAt(j) - '0';
                    j++;
                }
                i += num;
                if (i > m) return false;
            } else {
                if (word.charAt(i) != abbr.charAt(j)) return false;
                i++;
                j++;
            }
        }
        return i == m && j == n;
    }

    public static void main(String[] args) {
        L408_ValidWordAbbreviation sol =  new L408_ValidWordAbbreviation();
        //System.out.println(sol.validWordAbbreviation("apple", "a2le"));
        //System.out.println(validWordAbbreviation2("substitution", "s10n"));
    }


}
