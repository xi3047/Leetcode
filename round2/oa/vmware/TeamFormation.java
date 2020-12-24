package round2.oa.vmware;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 12/16/2020 12:18 AM
 * @topic round2.oa
 * @link
 * @description
 * VMWare
 */
public class TeamFormation {
    public static int teamFormation(int[] players, int k, int low, int high) {
        List<Integer> qualified = new LinkedList<>();
        for (int player : players) {
            if (player >= low && player <= high) {
                qualified.add(player);
            }
        }
        // find total number of combination from a list
        int[] total = new int[]{0};
        dfs(qualified, 0, total, new LinkedList<>(), k);
        return total[0];
    }

    private static void dfs(List<Integer> qualified, int i, int[] total, LinkedList<Integer> path, int k) {
        if (i == qualified.size()) {
            if (path.size() >= k)
                total[0] += 1;
            return;
        }
        dfs(qualified, i + 1, total, path, k);
        path.add(qualified.get(i));
        dfs(qualified, i + 1, total, path, k);
        path.remove(path.size() - 1);
    }

    public static void main(String[] args) {
        System.out.println(teamFormation(new int[]{12, 4, 6, 13, 5, 10}, 3, 4, 10));
    }
}
