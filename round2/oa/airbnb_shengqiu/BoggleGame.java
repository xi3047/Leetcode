package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. input is a char[][] represents the board, and a list of words
2. return the max number, or return the list of words that we can put on the board
without collision
3. word only contain 26 lowercase letters
4. each character can only be used once

Approach:
The idea is we start from each character in the board, we do a dfs.
We will search any words in the list. If we find one, we start from this
level of recursion tree. Start a new search from the board that has not been
marked as visited. For example, we find oath by visiting the path, then start
from here, we will start from other character except oath on the board to do a search.
{{'o','a','a','n'},
{'e','t','a','e'},
{'i','h','k','r'},
{'i','f','l','v'}};

Time: O((4^mn)^2))
level of recursion tree: m * n
each level has 4 branches
for each level, we start a new dfs, which has same time complexity
Therefore, total time complexity is O(4^(m*n) * 4^(m*n)))
Space: O(mn)
call stack takes O(mn)
temp list at most have O(k) where k is number of words, this case < mn
*/
class BoggleGame {

    private int m;
    private int n;
    private final int[] dirX = {-1, 1, 0, 0};
    private final int[] dirY = {0, 0, -1, 1};
    private int max;
    private List<String> maxList;
    private TrieNode root;

    public static void main(String[] args) {
        BoggleGame sol = new BoggleGame();
        char[][] board = {{'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}};
        String[] words = {"oath","ak","klf", "ii", "rena"};
        sol.findWords(board, words);
        System.out.println(sol.max);
        System.out.println(sol.maxList);
    }

    public void findWords(char[][] board, String[] words) {
        this.m = board.length;
        this.n = board[0].length;
        this.max = 0;
        this.root = buildTrie(words);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(list, board, i, j, root);
            }
        }
    }

    private void dfs(List<String> list, char[][] board, int i, int j, TrieNode p) {
        if (!inBound(i, j) || board[i][j] == '#' || p.next[board[i][j] - 'a'] == null) {
            return;
        }
        char c = board[i][j];
        p = p.next[c - 'a'];
        board[i][j] = '#';
        if (p.word != null) {
            list.add(p.word);
            if (max < list.size()) {
                this.max = list.size();
                this.maxList = new ArrayList<>(list);
            }
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    dfs(list, board, x, y, root); // use root instead of p !!!
                }
            }
            list.remove(list.size() - 1);
            board[i][j] = c; // remove this to speed up
            return;
        }
        for (int k = 0; k < 4; k++) {
            dfs(list, board,i + dirX[k], j + dirY[k], p);
        }
        board[i][j] = c;
    }

    private boolean inBound(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (cur.next[c - 'a'] == null) {
                    cur.next[c - 'a'] = new TrieNode();
                }
                cur = cur.next[c - 'a'];
            }
            cur.word = word;
        }
        return root;
    }
}

class TrieNode {
    TrieNode[] next;
    String word;
    public TrieNode() {
        this.next = new TrieNode[26];
        this.word = null;
    }
}

