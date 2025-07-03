package round4.bdfs;

import java.util.*;

public class L721_AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts == null || accounts.isEmpty()) return res;

        Map<String, List<String>> emailGraph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        // build graph
        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);

                emailGraph.putIfAbsent(email, new ArrayList<>());
                if (i == 1) continue;
                String prevEmail = account.get(i - 1);
                emailGraph.get(email).add(prevEmail);
                emailGraph.get(prevEmail).add(email);
            }
        }

        // dfs
        Set<String> visited = new HashSet<>();
        for (String email : emailGraph.keySet()){
            if (!visited.contains((email))) {
                List<String> group = new ArrayList<>();
                dfs(email, group, visited, emailGraph);
                Collections.sort(group);
                group.add(0, emailToName.get(email));
                res.add(group);
            }
        }

        return res;

    }
    private void dfs(String email, List<String> group, Set<String> visited, Map<String, List<String>> graph) {
        if (visited.contains(email)) return;

        visited.add(email);     // Mark current as visited
        group.add(email);       // Add to result group

        for (String neighbor : graph.get(email)) {
            dfs(neighbor, group, visited, graph);  // Recurse without redundant check
        }
    }
}
