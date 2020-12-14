package round2.bdfs;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 12/8/2020 11:36 PM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/accounts-merge/
 * @description
 */
public class L721_AccountsMerge {
    /*
    DFS
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts == null || accounts.size() == 0) return res;

        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // build graph
        for (List<String> list : accounts) {
            String name = list.get(0);
            for (int i = 1; i < list.size(); i++) {
                String email = list.get(i);
                emailToName.put(email, name);

                graph.putIfAbsent(email, new HashSet<>());
                if (i == 1) continue;
                graph.get(email).add(list.get(i-1));
                graph.get(list.get(i-1)).add(email);
            }
        }

        // search
        Set<String> visited = new HashSet<>();
        for (String email : emailToName.keySet()) {
            if (visited.add(email)) {
                List<String> path = new ArrayList<>();
                path.add(email);
                dfs(email, path, visited, graph);
                Collections.sort(path);
                path.add(0, emailToName.get(email));
                res.add(path);
            }
        }
        return res;
    }

    private void dfs(String email, List<String> path, Set<String> visited, Map<String, Set<String>> graph) {
        for (String neighbor : graph.get(email)) {
            if (visited.add(neighbor)) {
                path.add(neighbor);
                dfs(neighbor, path, visited, graph);
            }
        }
    }


    /*
    Union Find
     */
    public List<List<String>> accountsMerge2(List<List<String>> acts) {
        Map<String, String> owner = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, TreeSet<String>> unions = new HashMap<>();
        for (List<String> a : acts) {
            for (int i = 1; i < a.size(); i++) {
                parents.put(a.get(i), a.get(i));
                owner.put(a.get(i), a.get(0));
            }
        }
        for (List<String> a : acts) {
            String p = find(a.get(1), parents);
            for (int i = 2; i < a.size(); i++)
                parents.put(find(a.get(i), parents), p);
        }
        for(List<String> a : acts) {
            String p = find(a.get(1), parents);
            if (!unions.containsKey(p)) unions.put(p, new TreeSet<>());
            for (int i = 1; i < a.size(); i++)
                unions.get(p).add(a.get(i));
        }
        List<List<String>> res = new ArrayList<>();
        for (String p : unions.keySet()) {
            List<String> emails = new ArrayList(unions.get(p));
            emails.add(0, owner.get(p));
            res.add(emails);
        }
        return res;
    }
    private String find(String email, Map<String, String> parent) {
        return parent.get(email) == email ? email : find(parent.get(email), parent);
    }
}
