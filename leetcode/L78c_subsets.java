package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-04
    @time:   00:40

 */
/*
    Leetcode 78 return all possible subsets given an array of ints/chars
    Given a set of distinct integers, nums, return all possible subsets (the power set).
    Note: The solution set must not contain duplicate subsets.
    Example:
    Input: nums = [a,b,c]
    Output:
    [
      [a],
      [b],
      [c],
      [a,b,c],
      [a,b],
      [a,c],
      [b,c],
      []
    ]
*/
import org.junit.Test;

import java.util.*;

public class L78c_subsets {
    public List<String> subsets(char[] array) {
        List<String> res = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        dfsHelper1(array, index, sb, res);
        return res;
    }

    // Solution 1, using DFS with one branch
    private void dfsHelper1(char[] array, int index, StringBuilder sb, List<String> res) {
        res.add(sb.toString());
        for (int j = index; j < array.length; j++) {
            sb.append(array[j]);
            dfsHelper1(array, j+1, sb, res);
            // wall
            sb.deleteCharAt(sb.length() - 1); //remove/ setLen 返回到上一层call的状态
        }
        // return
    }

    //Solution 2, DFS, with two branches
    private void dfsHelper2(char[] array, int index, StringBuilder sb, List<String> result) {
        // if there is null node at next level, we add the stringbuilder to the result
        if (index == array.length) {
            result.add(sb.toString()); // deep copy, arr.clone()
            return;
        }
        // case1, add character at index to the stringbuilder
        sb.append(array[index]);
        dfsHelper2(array, index + 1, sb, result);
        // wall
        // remove the added character when backtracking to the previous level
        sb.deleteCharAt(sb.length() - 1);
        // case2, add nothing
        dfsHelper2(array, index + 1, sb, result);
    }

    // Solution 3, using BFS, two branches
    public List<String> subsets3(char[] array) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        int index =0;
        while (!queue.isEmpty() && index < array.length) {
            int size = queue.size();
            while (size-- > 0) {
                StringBuilder cur = new StringBuilder(queue.poll());
                queue.offer(cur.toString());
                cur.append(array[index]);
                queue.offer(cur.toString());
            }
            index++;
        }
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }

    // Solution 4: using BFS, one branch
    public List<String> subsets4(char[] array) {
        List<String> res = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer("");
        res.add("");
        int index = 0;
        while (!queue.isEmpty() && index < array.length) {
            int size = queue.size();
            int curIndex = index;
            while (size-- > 0) {
                StringBuilder cur = new StringBuilder(queue.poll());
                for (int i = curIndex; i < array.length; i++) {
                    cur.append(array[i]);
                    queue.offer(cur.toString());
                    res.add(cur.toString());
                    cur.deleteCharAt(cur.length() - 1);
                }
                curIndex++;
            }
            index++;
        }
        return res;
    }

    
    @Test
    public void test() {
        char[] array = new char[]{'a', 'b', 'c'};
        List<String> subsets = subsets4(array);
        int counter = 0;
        for (String s : subsets) {
            System.out.println(counter + ":" + s);
            counter++;
        }
    }
}
