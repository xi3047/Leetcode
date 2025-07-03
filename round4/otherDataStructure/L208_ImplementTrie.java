package round4.otherDataStructure;

public class L208_ImplementTrie {
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char c: word.toCharArray()) {
               if (cur.links[c - 'a'] == null) {
                   cur.links[c - 'a'] = new TrieNode();
               }
               cur = cur.links[c - 'a'];
            }
            cur.isWord = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.links[c - 'a'] == null) return false;
                cur = cur.links[c - 'a'];
            }
            return cur.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.links[c - 'a'] == null) return false;
                cur = cur.links[c - 'a'];
            }
            return true;
        }
    }

    class TrieNode {
        TrieNode[] links;
        boolean isWord;

        TrieNode() {
            links = new TrieNode[26];
        }
    }
}
