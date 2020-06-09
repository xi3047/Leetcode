package leetcode;

public class L13_RomanToInteger {
    // Solution 1
    public int romanToInt(String s) {
        if (s == null || s.length() == 0) return 0;
        int result = 0;
        if (s.indexOf("CM") != -1) result -= 200;
        if (s.indexOf("CD") != -1) result -= 200;
        if (s.indexOf("XC") != -1) result -= 20;
        if (s.indexOf("XL") != -1) result -= 20;
        if (s.indexOf("IX") != -1) result -= 2;
        if (s.indexOf("IV") != -1) result -= 2;
        for (char c : s.toCharArray()) {
            if (c =='M') result += 1000;
            else if (c == 'D') result += 500;
            else if (c == 'C') result += 100;
            else if (c == 'L') result += 50;
            else if (c == 'X') result += 10;
            else if (c == 'V') result += 5;
            else if (c == 'I') result += 1;
        }
        return result;
    }


    // Solution 2
    private int getVal(char c){
        switch (c){
            case 'M':
                return 1000;
            case 'D':
                return 500;
            case 'C':
                return 100;
            case 'L':
                return 50;
            case 'X' :
                return 10;
            case 'V':
                return 5;
            case 'I':
                return 1;
        }
        throw new IllegalArgumentException("unsupported character");
    }

    public int romanToInt2(String s) {
        int res = 0;
        if(s.length() == 0) return res;
        for (int i = 0; i < s.length() - 1; i++) {
            int cur = getVal(s.charAt(i));
            int nex = getVal(s.charAt(i+1));
            if(cur < nex){
                res -= cur;
            }else{
                res += cur;
            }
        }
        return res + getVal(s.charAt(s.length()-1));
    }
}
