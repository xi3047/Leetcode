package round2.oa.airbnb_shengqiu;

import java.util.*;

/*
Assumption:
1. The given input is a list of string in sequence
2. what if there is multiple valid order, return one valid order of the character
3. what if there is no valid order exists? => return ""

Approach:
So, in the high level, we are given a list of words in some sequence we do not know.
so, we can find the relationship by comparing with each consecutive words in the list.

for example: abcd, abdf are consecutive word, character a and b are same, we can not
get any information here. But the first different character is c and d, we know c is
before d. So, we can use this information to build a graph and get indegree for each node.
Then based on this, we can get the invalid oreder we want.

Time: O(nl)
average length of word: l
length of list: n
build graph and calculate indegree takes O(nl)
topo sort: O(V + E) where V is number of unique character <= nl, E = n
so, time complexity is O(nl) + O(V + n) = O(nl)

Space:  O(nl)
===================================
Follow up:
1. how to find all valid order:
The idea is for this solution, the queue store the node that has 0 indegree, we
just pick the first one. But if we want to find all valid order, we need to try
all node. So, we need to change the queue to a set, and do a backtrack to try all
possible cases.
for example:
for the first level of recursion tree we have [1,2,3],  we pick 1, which give us 4,5.
so, the second level we have [2,3,4,5]. Or we can pick 2 or 3 in the first level.

2. How to solve this problem with DFS
we still need to build the graph for the character from consecutive word.
But we do not calculate the indegree. Start from any unvisited node, do a post-order
traversal, and append the character.
For example:
A -> B -> C
B -> D
start from B, visit C, "C"
visit D, append D: "CD"
then we can append B: "CDB" , we should visit A, A can not go further, append A: "CDBA"
Then reverse the string to get the right order.
*/

public class AlienDict {

    public static void main(String[] args) {
        AlienDict sol = new AlienDict();
        List<String> words = Arrays.asList("aa", "ab", "ac", "bb");
        System.out.println(sol.findOrder(words));
    }

    public String findOrder(List<String> words) {
        if (words == null || words.size() == 0) return "";
        Map<Character, Integer> degree = new HashMap<>();
        Map<Character, Set<Character>> graph = new HashMap<>();
        // init graph and degree map
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree.putIfAbsent(c, 0);
                graph.putIfAbsent(c, new HashSet<>());
            }
        }
        // build the graph and degree map
        for (int i = 0; i < words.size() - 1; i++) {
            String word1 = words.get(i);
            String word2 = words.get(i + 1);
            int len = Math.min(word1.length(), word2.length());
            for (int j = 0; j < len; j++) {
                char c1 = word1.charAt(j);
                char c2 = word2.charAt(j);
                if (c1 != c2) {
                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }
        // topological sort
        Queue<Character> queue = new LinkedList<>();
        for (char key : degree.keySet()) {
            if (degree.get(key) == 0) {
                queue.offer(key);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            // generate neighbors
            for (char nei : graph.get(c)) {
                degree.put(nei, degree.get(nei) - 1);
                if (degree.get(nei) == 0) {
                    queue.offer(nei);
                }
            }
        }
        // if there is a cycle, means the sb.length() != degree.size();
        if (degree.size() != sb.length()) {
            return "";
        }
        return sb.toString();
    }
}