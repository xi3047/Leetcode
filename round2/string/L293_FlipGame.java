package round2.string;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/12/21 8:20 PM
 * @topic round2.string
 * @link https://leetcode.com/problems/flip-game/
 * @description
 * You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.
 *
 * Write a function to compute all possible states of the string after one valid move.
 *
 * Example:
 *
 * Input: s = "++++"
 * Output:
 * [
 *   "--++",
 *   "+--+",
 *   "++--"
 * ]
 */
public class L293_FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '+' && s.charAt(i+1) == '+') {
                StringBuilder sb = new StringBuilder(s);
                sb.setCharAt(i, '-');
                sb.setCharAt(i + 1, '-');
                res.add(sb.toString());
            }
        }
        return res;
    }
}
