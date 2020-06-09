package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
    @author: Xi Zhang
    @date:   2019-03-06
    @time:   13:46

    40 ms solution
 */
public class L212_WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<String>();
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(res, board, visited, "", i, j, trie);
            }
        }

        return new ArrayList<>(res);
    }

    public void dfs(Set<String> res, char[][] board, boolean[][] visited, String str, int x, int y, Trie trie) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y]) return;

        str += board[x][y];
        if (!trie.startsWith(str)) return;

        if (trie.search(str)) {
            res.add(str);
        }

        visited[x][y] = true;
        dfs(res, board, visited, str, x - 1, y, trie);
        dfs(res, board, visited, str, x + 1, y, trie);
        dfs(res, board, visited, str, x, y - 1, trie);
        dfs(res, board, visited, str, x, y + 1, trie);
        visited[x][y] = false;
    }

    class TrieNode {
        public TrieNode[] children = new TrieNode[26];
        public String item = "";

        // Initialize your data structure here.
        public TrieNode() {
        }
    }
    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TrieNode();
                }
                node = node.children[c - 'a'];
            }
            node.item = word;
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                if (node.children[c - 'a'] == null) return false;
                node = node.children[c - 'a'];
            }
            return node.item.equals(word);
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for (char c : prefix.toCharArray()) {
                if (node.children[c - 'a'] == null) return false;
                node = node.children[c - 'a'];
            }
            return true;
        }
    }

    @Test
    public void test() {
        char[][] board = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = { "pea", "eat", "rain", "oath"};
        System.out.println(findWords(board, words));
    }
}
