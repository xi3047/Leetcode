package round4.otherDataStructure;


import java.util.*;

public class L588_DesignInMemoryFileSystem {
    class FileSystem {
        class Node {
            boolean isFile = false;
            StringBuilder content = new StringBuilder();
            Map<String, Node> children = new TreeMap<>();
        }

        private final Node root;

        private Node traverse(String path) {
            Node cur = root;
            if (path.equals("/")) return root;
            String[] parts = path.split("/");
            for (String part : parts) {
                if (part.isEmpty()) continue;
                cur.children.putIfAbsent(part, new Node());
                cur = cur.children.get(part);
            }
            return cur;
        }

        public FileSystem() {
            root = new Node();
        }

        // list all path under cur directory
        public List<String> ls(String path) {
            Node cur = traverse(path);
            if (cur.isFile) {
                String[] parts = path.split("/");
                List<String> res = new ArrayList<>();
                res.add(parts[parts.length - 1]);
                return res;
            }
            return new ArrayList<>(cur.children.keySet());
        }

        public void mkdir(String path) {
            traverse(path);
        }

        public void addContentToFile(String filePath, String content) {
            Node node = traverse(filePath);
            node.isFile = true;
            node.content.append(content);
        }

        public String readContentFromFile(String filePath) {
            Node node = traverse(filePath);
            return node.content.toString();
        }
    }

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * List<String> param_1 = obj.ls(path);
 * obj.mkdir(path);
 * obj.addContentToFile(filePath,content);
 * String param_4 = obj.readContentFromFile(filePath);
 */

}
