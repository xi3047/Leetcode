package leetcode;

import org.junit.Test;

import java.util.*;

public class L269_AlienDictionaryV2 {
    public String alienOrder(String[] words) {
            // Initalize graph
        Map<Character, Vertex> vertexMap = new HashMap<>();

        for (int i = 0; i < words.length - 1; i++) {
            String curWord = words[i];
            String nextWord = words[i + 1];
            int minLength = Math.min(curWord.length(), nextWord.length());

            for (int j = 0; j < minLength; j++) {
                char curChar = curWord.charAt(j);
                char nextChar = nextWord.charAt(j);
                if (curChar != nextChar) {
                    if (!vertexMap.containsKey(curChar)) {
                        vertexMap.put(curChar, new Vertex(curChar));
                    }
                    vertexMap.get(curChar).nexts.add(nextChar);
                    if (!vertexMap.containsKey(nextChar)) {
                        vertexMap.put(nextChar, new Vertex(nextChar));
                    }
                    break;
                } else {
                    if (!vertexMap.containsKey(curChar)) {
                        vertexMap.put(curChar, new Vertex(curChar));
                    }
                }
            }
            // post processing
            int min1 = minLength;
            int min2 = minLength;
            while (min1  < curWord.length()) {
                vertexMap.putIfAbsent(curWord.charAt(min1), new Vertex(curWord.charAt(min1)));
                min1++;
            }
            while (min2  < nextWord.length()) {
                vertexMap.putIfAbsent(nextWord.charAt(min2), new Vertex(nextWord.charAt(min2)));
                min2++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Character ch : vertexMap.keySet()) {
            if (vertexMap.get(ch).nexts.size() != 0) {
                // check if the c
                if (containsCycle(sb, vertexMap.get(ch), vertexMap)) {
                    return "";
                }
            }
        }
        for (Character ch : vertexMap.keySet()) {
            if (vertexMap.get(ch).nexts.size() == 0) {
                if (sb.toString().indexOf(ch) != -1) continue;
                insertToSB(sb, ch);
            }
        }

        return sb.toString();
    }

    private void insertToSB(StringBuilder sb, char ch) {
        if (sb.length() == 0){
            sb.append(ch);
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            if ( ch - 'a' < sb.charAt(i) - 'a' ) {
                sb.insert(i, ch);
                return;
            } else if (i == sb.length() - 1) {
                sb.append(ch);
                return;
            }
        }
    }

    @Test
    public void testInsert() {
        StringBuilder sb = new StringBuilder();
        sb.append("ABD");
        insertToSB(sb, 'F');
        System.out.println(sb.toString());
    }


    private boolean containsCycle(StringBuilder sb, Vertex cur, Map<Character, Vertex> vertexMap) {
        if (cur.status == Status.DONE) {
            return false;
        }
        if (cur.status == Status.PROCESSING) {
            return true;
        }
        cur.status = Status.PROCESSING;
        if (cur.nexts != null) {
            for (Character ch : cur.nexts) {
                if (containsCycle(sb, vertexMap.get(ch), vertexMap)) {
                    return true;
                }
            }
        }

        sb.insert(0, cur.ch);
        cur.status = Status.DONE;
        return false;
    }

    class Vertex {
        public char ch;
        public Status status;
        public Set<Character> nexts;

        public Vertex(char ch) {
            this.ch = ch;
            this.status = Status.INITIAL;
            this.nexts = new HashSet<>();
        }
    }

    enum Status {
        INITIAL, PROCESSING, DONE;
    }


    @Test
    public void test() {
        String[] words = {"WRT", "WRF", "ER", "ETT", "RFTT"};
        System.out.println(alienOrder(words));
    }

    @Test
    public void test2() {
        String[] words = {"Z", "X"};
        System.out.println(alienOrder(words));
    }

    @Test
    public void test3() {
        String[] words = {"AB", "ADC"};
        System.out.println(alienOrder(words));
    }

    @Test
    public void test4() {
        String[] words = {"ZA", "ZB", "CA", "CB"};
        System.out.println(alienOrder(words));
    }

}
