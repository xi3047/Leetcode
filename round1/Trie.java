package round1;
/*
    @author: Xi Zhang
    @date:   2019-03-06
    @time:   14:25
    Leetcode 208. Implement TrieNode(Prefix Tree)
 */
 class Trie {
     private TrieNode root;

     public Trie() {
         root = new TrieNode();
     }

     // Insert a word into the trie, Time complexity : O(m), m is the key length
     public void insert(String word) {
         TrieNode node = root;
         for (char c : word.toCharArray()) {
             if (!node.containsKey(c)) {
                 node.put(c, new TrieNode());
             }
             node = node.get(c);
         }
         node.setEnd();
     }

     // search a prefix or whole key in trie and
     // returns the node where search ends
     private TrieNode searchPrefix(String word) {
         TrieNode node = root;
         for (char c : word.toCharArray()) {
             if (node.containsKey(c)) {
                 node = node.get(c);
             } else {
                 return null;
             }
         }
         return node;
     }

     // returns if the word is in the trie
     public boolean search(String word) {
         TrieNode node = searchPrefix(word);
         return node != null && node.isEnd();
     }

     // returns if there is any word in the trie
     // that starts with the given prefix
     public boolean startWith(String prefix) {
         TrieNode node = searchPrefix(prefix);
         return node != null;
     }
}
