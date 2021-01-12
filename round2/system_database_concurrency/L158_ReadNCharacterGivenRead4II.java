package round2.system_database_concurrency;

/**
 * @author Xi Zhang
 * @date 1/11/21 5:01 PM
 * @topic round2.system_database_concurrency
 * @link https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
 * @description
 */
public class L158_ReadNCharacterGivenRead4II {
    private int pointer = 0;
    private int len = 0;
    private char[] tmp = new char[4];

    public int read(char[] buf, int n) {
        int index = 0;
        while (index < n) {
            if (pointer == 0) {
                len = read4(tmp);
            }
            if (len == 0) break;
            while (index < n && pointer < len) {
                buf[index++] = tmp[pointer++];
            }
            if (pointer >= len) pointer = 0;
        }
        return index;

    }

    private int read4(char[] tmp) {
        return 0;
    }
}
