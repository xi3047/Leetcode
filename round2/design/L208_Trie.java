package round2.design;

/**
 * @author Xi Zhang
 * @date 1/15/21 7:22 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/implement-trie-prefix-tree/
 * @description
 * Implement a trie with insert, search, and startsWith methods.
 *
 * Example:
 *
 * Trie trie = new Trie();
 *
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 */
public class L208_Trie {
    class Trie {
        private TrieNode root;
        /** Initialize your data structure here. */
        public Trie() {
            root = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.links[c -'a'] == null) {
                    cur.links[c - 'a'] = new TrieNode();
                }
                cur = cur.links[c - 'a'];
            }
            cur.isword = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.links[c - 'a'] == null) return false;
                else cur = cur.links[c - 'a'];
            }
            return cur.isword;
        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                if (cur.links[c - 'a'] == null) return false;
                else cur = cur.links[c - 'a'];
            }
            return true;
        }

        class TrieNode {
            TrieNode[] links;
            boolean isword;

            public TrieNode() {
                links = new TrieNode[26];
            }
        }
    }
}
