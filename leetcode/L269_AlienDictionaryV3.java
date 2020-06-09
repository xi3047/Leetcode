package leetcode;

import org.junit.Test;

import java.util.*;

/**
 * Created by billyxiao on 5/1/19.
 */
public class L269_AlienDictionaryV3 {
    enum Status{
        INIT, PROC, DONE
    }
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0) return "";
        if(words.length == 1) return words[0];
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();
        Map<Character, Status> map = new HashMap<>();
        Map<Character, List<Character>> graph = buildGraph(words, map, set);
        for(char c : set){
            if(containsCycle(c, words,map, graph, sb)){
                return "";
            }
        }
        return sb.toString();

    }

    private Map<Character, List<Character>> buildGraph(String[] words, Map<Character, Status> map, Set<Character> set){
        Map<Character, List<Character>> graph = new HashMap<>();
        for(int i = 1; i < words.length; i++){
            String cur = words[i];
            String prev = words[i-1];
            int idx1 = 0, idx2 = 0;
            while(idx1 < prev.length() && idx2 < cur.length()){
                char c1 = prev.charAt(idx1++), c2 = cur.charAt(idx2++);
                set.add(c2);
                set.add(c1);
                List<Character> l1 = graph.getOrDefault(c1, new ArrayList<Character>());
                List<Character> l2 = graph.getOrDefault(c2, new ArrayList<Character>());
                if(c1 != c2){
                    l1.add(c2);
                    graph.put(c1, l1);
                    graph.put(c2, l2);
                    break;
                }
                graph.put(c1, l1);
                graph.put(c2, l2);
            }

            while(idx1 < prev.length()){
                char c = prev.charAt(idx1++);
                set.add(c);
                List<Character> l = graph.getOrDefault(c, new ArrayList<Character>());
                graph.put(c, l);
            }

            while(idx2 < cur.length()){
                char c = cur.charAt(idx2++);
                set.add(c);
                List<Character> l = graph.getOrDefault(c, new ArrayList<Character>());
                graph.put(c, l);
            }
        }

        for(char c : set){
            map.put(c, Status.INIT);
            // System.out.print(c);
        }
        return graph;

    }

    private boolean containsCycle(char cur, String[] words, Map<Character, Status> map,  Map<Character, List<Character>> graph, StringBuilder sb){
        Status status = map.get(cur);
        if(status == Status.PROC) return true;
        if(status == Status.DONE) return false;
        map.put(cur, Status.PROC);
        for(char next : graph.get(cur)){
            if(containsCycle(next, words, map, graph, sb)){
                return true;
            }
        }
        // graph.remove(cur);
        map.put(cur, Status.DONE);
        sb.insert(0, cur);
        return false;
    }


    @Test
    public void test() {
        String[] words = {"ZA", "ZB" , "CA", "CB"};
        System.out.println(alienOrder(words));

    }
}
