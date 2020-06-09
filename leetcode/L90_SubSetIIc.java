package leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class L90_SubSetIIc {
    public List<String> subsets(char[] array) {
        List<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        dfsHelper2(array, index, sb, res);
        return res;
    }
    private void dfsHelper2(char[] array, int index, StringBuilder sb, List<String> result) {
        // if there is null node at next level, we add the stringbuilder to the result
        if (index == array.length) {
            result.add(sb.toString()); // deep copy, arr.clone()
            return;
        }

        sb.append(array[index]);
        dfsHelper2(array, index + 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);

        while (index < array.length - 1 && array[index] == array[index + 1]) {
            index++;
        }
        dfsHelper2(array, index + 1, sb, result);

    }

    @Test
    public void test() {
        char[] array = new char[]{'a', 'b', 'b'};
        List<String> subsets = subsets(array);
        int counter = 1;
        for (String s : subsets) {
            System.out.println(counter + ": " + s);
            counter++;
        }
    }
}
