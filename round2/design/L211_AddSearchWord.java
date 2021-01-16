package round2.design;

/**
 * @author Xi Zhang
 * @date 1/15/21 9:33 PM
 * @topic round2.design
 * @link https://leetcode.com/problems/design-add-and-search-words-data-structure/
 * @description
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
 * word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 */
public class L211_AddSearchWord {
    class WordDictionary {
        TrieNode root;
        /** Initialize your data structure here. */
        public WordDictionary() {
            root = new TrieNode();
        }

        public void addWord(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.links[c - 'a'] == null) {
                    cur.links[c - 'a'] = new TrieNode();
                }
                cur = cur.links[c - 'a'];
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            return match(word.toCharArray(), 0, root);
        }

        private boolean match(char[] chs, int index, TrieNode node) {
            if (index == chs.length) {
                return node.isEnd;
            }
            char c = chs[index];
            if (chs[index] == '.') {
                for (int k = 0; k < node.links.length; k++) {
                    if (node.links[k] != null && match(chs, index + 1, node.links[k])) {
                        return true;
                    }
                }

            } else {
                return node.links[c - 'a'] != null && match(chs, index + 1, node.links[c - 'a']);
            }
            return false;
        }

        class TrieNode {
            TrieNode[] links;
            boolean isEnd;

            public TrieNode() {
                links = new TrieNode[26];
            }
        }
    }

}
