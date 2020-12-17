package round2.oa;

/**
 * @author Xi Zhang
 * @date 12/14/2020 8:50 PM
 * @topic round2.oa
 * @link
 * @description VMWare OA question 2
 *
 * Get all subsequences of a given string, excluding empty strings.
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Subsequence
{

    public List<String> printAllSubsequence(String str) {
        List<String> res = new ArrayList<>();
        subsequence(str, new StringBuilder(), res, 0);
        return res;
    }

    // Function computes all the subsequence of an string

    public void subsequence(String str,  StringBuilder sb, List<String> list, int index ) {
        if (index == str.length()) {
            if (sb.length() == 0) return;
            list.add(sb.toString());
            return;
        }

        subsequence(str, sb, list, index + 1);
        sb.append(str.charAt(index));
        subsequence(str, sb, list, index + 1);
        sb.setLength(sb.length() - 1);
    }

    @Test
    public void test()
    {
        System.out.println(printAllSubsequence("ba"));
    }
}
