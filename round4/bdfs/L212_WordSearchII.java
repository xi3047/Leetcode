package round4.bdfs;



import java.util.ArrayList;
import java.util.List;

public class L212_WordSearchII {

    public List<String> findWords(char[][] board, String[] words) {
        TrieNode root = buildTrie(words);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(root, board, res, 0 ,0);
            }
        }
        return res;
    }

    private void dfs(TrieNode root, char[][] board, List<String> res, int i, int j) {
        if (i < 0 || i > board.length || j < 0 || j > board[0].length || board[i][j] == '.') return;
        char c = board[i][j];
        if (root.children[c -'a'] == null) return;

        root = root.children[c - 'a'];
        if (root.word != null) {
            res.add(root.word);
            root.word = null;
        }

        board[i][j] = '.';
        dfs(root, board, res, i + 1, j);
        dfs(root, board, res, i -1, j);
        dfs(root, board, res, i , j + 1);
        dfs(root, board, res, i , j - 1);
        board[i][j] = c;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word: words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.children[c - 'a'] == null) {
                    cur.children[c - 'a'] = new TrieNode();
                }
                cur = cur.children[c - 'a'];
            }
            cur.word = word;
        }
        return root;
    }

    static class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
            word = null;
        }
    }
}
