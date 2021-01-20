package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. The input is a list of list represents the menu
for example [["A", "14.99"], ["B","12.99"], ...]
2. given a target price, find all possible way to order so we just spend all of the money
3. Each item, we can order as many times as we want
4. There is no duplicate item
5. The price for each item >= 0
6. Target value >= 0
7. Precision to .0001

Approach:
The idea is to do a dfs, let me first draw the recursion tree to explain my idea
                50
           /    |   \
0  A    1(35)   2(30) 3(5)
        /   \
1  B  1(22) 2(11)
     /|\
2  C

3  D

4  E
...
Time: O(n^k) where k is the level of recursion tree. n is the max number of item we can buy with
recursion tree levels: number of items
for each level, we at most try target / smallest price times
Space: Auxiliary space complexity is O(n) for the temp list, O(k) for the call stack
n = target / smallest price, k = number of menu item
*/
class BMenuSum {

    private final static double EPSILON = .001;

    public static void main(String[] args) {
        List<List<String>> menu = new ArrayList<>();
        menu.add(Arrays.asList("A", "12.2"));
        menu.add(Arrays.asList("B", "11.3"));
        menu.add(Arrays.asList("C", "10.4"));
        menu.add(Arrays.asList("D", "8.5"));
        menu.add(Arrays.asList("E", "1.6"));
        Map<String, Double> map = new HashMap<>();
        map.put("A", 12.2);
        map.put("B", 11.3);
        map.put("C", 10.4);
        map.put("D", 8.5);
        map.put("E", 1.6);
        BMenuSum sol = new BMenuSum();
        List<List<String>> res = sol.order(menu, 100);
        for (List<String> list : res) {
            System.out.println(list);
        }
        System.out.println(res.size());
    }

    public List<List<String>> order(List<List<String>> lists, int target) {
        List<Dish> menu = new ArrayList<>();
        for (List<String> list : lists) {
            String id = list.get(0);
            double price = Double.parseDouble(list.get(1));
            menu.add(new Dish(id, price));
        }
        List<List<String>> res = new ArrayList<>();
        List<String> list = new ArrayList<>();
        findOrder(res, list, menu, 0, target);
        return res;
    }

    private void findOrder(List<List<String>> res, List<String> list, List<Dish> menu, int level, double rem) {
        if (level == menu.size()) {
            if (Math.abs(rem) < EPSILON) {
                res.add(new ArrayList<>(list));
            }
            return;
        }
        String item = menu.get(level).id;
        double price = menu.get(level).price;
        for (int i = 0; i * price < rem || Math.abs(i * price - rem) < EPSILON; i++) {
            for (int j = 0; j < i; j++) {
                list.add(item);
            }
            findOrder(res, list, menu, level + 1, rem - i * price);
            for (int j = 0; j < i; j++) {
                list.remove(list.size() - 1);
            }
        }
    }
}

class Dish {
    String id;
    double price;

    public Dish(String id, double price) {
        this.id = id;
        this.price = price;
    }
}
























