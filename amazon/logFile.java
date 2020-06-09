package amazon;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Write a function of method to return the junction boxes in the specified order
 *
 * Input: The input to the function consists of two arguments: numberOfBoxes, an integer representing the number of
 * junction boxes. boxlist, a list of Strings representing all the identifiers and version information
 *
 * Output: Return a list of strings representing the correctly ordered junction boxes
 *
 * Note:
 * The junction box identifier consists of only lower case English characters and numbers.
 *
 * Examples:
 * Input:
 * numberOfBoxes = 6
 * boxList = [ykc 82 01] [eo first qpx] [09z cat hamster] [06f 12 25 6] [az0 first qpx] [236 cat dog rabbit snake]
 *
 * Output:
 * [236 cat dog rabbit snake] [09z cat hamster] [az0 first qpx] [eo first qpx] [ykc 82 01] [06f 12 25 6]
 *
 * Explantion:
 * The four old generation junction boxes should come first, with the "cat dog rabbit snake" box coming before the
 * "cat hamster". Since the two boxes of type "first qpx" have the same version information, they should come next,
 * using the "az0" identifier to come before the "eo" identifier. Finally, the already upgraded junction boxes
 * should ome flat, in the original order they were provided in the file.
 */
public class logFile {
    private boolean isNewVersion(String box) {
        for(int i = box.indexOf(' ')+1; i < box.length(); i++) {
            if (Character.isSpaceChar(box.charAt(i))) {
                continue;
            }
            if (!Character.isDigit(box.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public List<String> orderdJunctionBoxes(int numberOfBoxes, List<String> boxList) {
        List<String> result = new ArrayList<>();
        if (numberOfBoxes <= 0 || boxList == null || boxList.size() <= 0 || boxList.size() != numberOfBoxes) {
            return result;
        }

        // Separate old version boxes vs new version boxes.
        List<String> newVersionBoxes = new ArrayList<>();
        for (String box: boxList) {
            if (isNewVersion(box)) {
                // put new version box into newVersionBoxes. No need to sort these.
                newVersionBoxes.add(box);
            } else {
                // put old version box into result. Sort it later.
                result.add(box);
            }
        }
        // Sort old version boxes.
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String v1 = o1.substring(o1.indexOf(' ')+1);
                String v2 = o2.substring(o2.indexOf(' ')+1);
                if (v1.equals(v2)) {
                    return o1.compareTo(o2);
                }
                return v1.compareTo(v2); // compa
            }
        });
        // Append new version boxes.
        result.addAll(newVersionBoxes);
        return result;
    }

    @Test
    public void test() {
        List<String> boxList = new ArrayList<>();
        boxList.add("ykc 82 01");
        boxList.add("eo first qpx");
        boxList.add("09z cat hamster");
        boxList.add("06f 12 25 6");
        boxList.add("az0 first qpx");
        boxList.add("236 cat dog rabbit snake");


        List<String> result = orderdJunctionBoxes(boxList.size(), boxList);

        for(String b : result) {
            System.out.println(b);
        }

    }


}
