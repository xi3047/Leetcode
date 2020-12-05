package round2.bdfs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 11/24/2020 5:11 PM
 * @topic round2.bdfs trie
 * @link
 * @description
 */
public class L212_WordSearch2 {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs (board, i, j, root, res);
            }
        }
        return res;
    }
    public void dfs(char[][] board, int i, int j, TrieNode t, List<String> res) {
        // cache current character
        char c = board[i][j];

        // failed case, if current ch is visited or current trieNode doesn't exist
        if (c == '#' || t.children[c - 'a'] == null) return;
        // success case
        t = t.children[c- 'a'];
        if (t.word != null) {
            res.add(t.word);
            t.word = null;
        }
        // dfs
        // mark as visited
        board[i][j] = '#';
        if (i > 0) dfs(board, i - 1, j ,t, res);
        if (j > 0) dfs(board, i, j - 1, t, res);
        if (i < board.length - 1) dfs(board, i + 1, j, t, res);
        if (j < board[0].length - 1) dfs(board, i, j + 1, t, res);
        board[i][j] = c;
    }


    public TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            char[] chs = word.toCharArray();
            TrieNode cur = root;
            for (char c : chs) {
                int i = c - 'a';
                if (cur.children[i] == null) cur.children[i] = new TrieNode();
                cur = cur.children[i];
            }
            cur.word = word;
        }
        return root;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word;
    }
}
