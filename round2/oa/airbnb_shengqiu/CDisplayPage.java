package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. The input is a list of string, return a list of string represents the paged list.
2. The record already sorted based on the score

Approach:
Since the list is sorted with score, we can just start from
the first record, and pick the string. We do not want to pick
record with same id if we can find enough to fill this page.
otherwise, we have to go back to pick record with same id.

So, we are going to use a iterator to traverse the list, use
a hash table to keep track of record we pick for each page.

Time: O(n^2) ~ O(n^3)
in the worst case hash table look up takes O(n)
n is the number of record in the list, we iterate the list
and remove element from it.
Space: O(n) for the hash table
*/
public class CDisplayPage {
    public static void main(String[] args) {
        CDisplayPage sol = new CDisplayPage();
        List<String> input = new ArrayList<>();
        input.add("1,28,310.6,SF");
        input.add("4,5,204.1,SF");
        input.add("20,7,203.2,Oakland");
        input.add("6,8,202.2,SF");
        input.add("6,10,199.1,SF");
        input.add("1,16,190.4,SF");
        input.add("6,29,185.2,SF");
        input.add("7,20,180.1,SF");
        input.add("6,21,162.1,SF");
        input.add("2,18,161.2,SF");
        input.add("2,30,149.1,SF");
        input.add("3,76,146.2,SF");
        input.add("2,14,141.1,San Jose");
        List<String> list = sol.displayPage(input, 5);
        for (String str : list) {
            System.out.println(str);
        }
    }

    public List<String> displayPage(List<String> input, int size) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Iterator<String> iter = input.iterator();
        int count = 0;
        while (iter.hasNext()) {
            String record = iter.next();
            String id = record.split(",")[0];
            if (!set.contains(id)) {
                iter.remove();
                set.add(id);
                res.add(record);
                count++;
            }
            if (count == size) {
                iter = input.iterator();
                set.clear();
                res.add("");
                count = 0;
            } else if (!iter.hasNext()) {
                iter = input.iterator();
                set.clear();
            }
        }
        return res;
    }
}
// Solution 2: O(n)
// public List<String> displayPage(List<String> input, int size) {
//     List<String> result = new ArrayList<>();
//     Map<String, Queue<String>> map = new HashMap<>();  // map of queue
//     for (String record : input) {
//         String hostId = record.split(",")[0];
//         map.putIfAbsent(hostId, new LinkedList<>());
//         map.get(hostId).offer(record);
//     }
//     // enqueue the first record of each host ID to queue
//     Queue<Node> queue = new LinkedList<>();
//     for (Map.Entry<String, Queue<String>> entry : map.entrySet()) {
//         queue.offer(new Node(entry.getKey(), entry.getValue().poll()));
//     }
//     int count = 0;
//     List<Node> temp = new ArrayList<>();
//     while (!queue.isEmpty()) {
//         Node head = queue.poll();
//         temp.add(head);
//         count++;
//         if (!map.get(head.hostId).isEmpty()) {
//             queue.offer(new Node(head.hostId, map.get(head.hostId).poll()));
//         }
//         if (count == size) {
//             copy(temp, result);
//             count = 0;
//         }
//     }
//     if (!temp.isEmpty()) {
//         copy(temp, result);
//     }
//     return result;
// }

// private void copy(List<Node> temp, List<String> res) {
//     Collections.sort(temp, (o1, o2) -> {
//        return  Double.compare(Double.parseDouble(o2.record.split(",")[2]),
//                               Double.parseDouble(o1.record.split(",")[2]));
//     });
//     for (Node node : temp) {
//         res.add(node.record);
//     }
//     res.add("");
//     temp.clear();
// }

// class Node {
//     String hostId;
//     String record;

//     public Node(String hostId, String record) {
//         this.hostId = hostId;
//         this.record = record;
//     }
// }
