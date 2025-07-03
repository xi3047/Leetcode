package round4.otherDataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class L1233_RemoveSubFoldersFromFilesystem {

    class Trie {
        Map<String, Trie> map = new HashMap<>();
        String word = null;
    }

    private List<String> result;
    private void getResults(Trie root) {
        for (Map.Entry<String, Trie> entry: root.map.entrySet()) {
            if (entry.getValue().word == null) {
                getResults(entry.getValue());
            } else {
                result.add(entry.getValue().word);
            }
        }
    }

    public List<String> removeSubfolders(String[] folders) {
        Trie root = new Trie();
        for (String folder : folders) {
            Trie cur = root;
            String[] parts = folder.substring(1).split("/");
            for (String part : parts) {
                if (!cur.map.containsKey(part)) {
                    cur.map.put(part, new Trie());
                }
                cur = cur.map.get(part);
            }
            cur.word = folder;
        }

        getResults(root);
        return result;
    }

}
