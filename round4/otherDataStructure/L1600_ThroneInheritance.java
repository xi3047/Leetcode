package round4.otherDataStructure;

import java.util.*;

public class L1600_ThroneInheritance {
    class ThroneInheritance {
        private String king;
        private HashMap<String, List<String>> family;
        private Set<String> dead;

        public ThroneInheritance(String kingName) {
            this.king = kingName;
            family = new HashMap<>();
            dead = new HashSet<>();
        }

        public void birth(String parentName, String childName) {
            family.putIfAbsent(parentName, new ArrayList<>());
            family.get(parentName).add(childName);
        }

        public void death(String name) {
            dead.add(name);
        }

        public List<String> getInheritanceOrder() {
            List<String> order = new ArrayList<>();
            dfs(king, order);
            return order;
        }

        private void dfs(String name, List<String> order) {
            if (!dead.contains(name)) {
                order.add(name);
            }
            for (String child : family.getOrDefault(name, new ArrayList<>())) {
                dfs(child, order);
            }
        }
    }
}
