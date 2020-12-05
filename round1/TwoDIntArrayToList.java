package round1;
/*
    @author: Xi Zhang
    @date:   2019-03-12
    @time:   00:27
    Helper function to convert 2D int array to List of List of Integers
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TwoDIntArrayToList {
    public static List<List<Integer>> convert2DIntArrayToList(int[][] arr) {
        List<List<Integer>> allLists = new ArrayList<>();
        for (int[] r : arr) {
            List<Integer> list = Arrays.stream(r).boxed().collect(Collectors.toList());
            allLists.add(list);
        }
        return allLists;
    }
}
