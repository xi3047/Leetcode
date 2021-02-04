package round2.oa;

import java.util.function.BiConsumer;

/**
 * @author Xi Zhang
 * @date 12/18/2020 8:55 PM
 * @topic round2.oa
 * @link
 * @description
 */
public class Test {


    static int[] count1 = new int[26];
    static int[] count2 = new int[26];

    static String mergeStrings(String s1, String s2) {

        for (char c : s1.toCharArray()){
            count1[c - 'a']++;
        }
        for (char c : s2.toCharArray()) {
            count2[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0,  j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (compareCount(s1.charAt(i), s2.charAt(j)) > 0) {
                sb.append(s2.charAt(j));
                j++;
            } else {
                sb.append(s1.charAt(i));
                i++;
            }
        }
        if (i== s1.length()) {
            sb.append(s2.substring(j));
        } else if (j == s2.length()) {
            sb.append(s1.substring(i));
        }

        return sb.toString();
    }

    static int compareCount(Character a, Character b) {
        if (count1[a - 'a'] != count2[b - 'a']) {
            return count1[a - 'a'] - count2[b - 'a'];
        } else {
            return a.compareTo(b);
        }
    }


    public static void main(String[] args)
    {
//        System.out.println(mergeStrings("super", "tower"));
//        System.out.println("flow".indexOf("eraerasdf"));

//        System.out.println(23 | 1);
//        BiConsumer<Integer, Integer> addTwo = (x, y) -> System.out.println(x + y);
//        addTwo.accept(3,4);
//        addTwo(1, 2, (x, y) -> System.out.println(x + y));
//        addTwo("Node", ".js", (x, y) -> System.out.println(x + y));
//
        int a = 0;
        int b = 1;
        System.out.println("HELLO WORLD");


    }
    static <T> void addTwo(T a1, T a2, BiConsumer<T, T> c) {
        c.accept(a1, a2);
    }
}
