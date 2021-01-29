package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. input is a list of string, represents my list of favorite
and a Map of list, Store friends name as key, and friend lists as value
2. return a list of friends, based on the similarity of favorite
follow up: return recommended city based on buddy list
3. the list does not contain duplicate

Approach:
So, the idea is use a set to store my list, and use a Map of set to
store my friends' list. Then for each friend, calculate the similarity,
add to a list that will sort based on similarity.

After we have a list of buddy sorted with similarity, then we can just
use the list to do the recommendation, from the most similar buddy, and
get the cities from his list, remove my list, which should be the recommended
city I can go.

(Auxilary)
Time: O(mn) ~ O(mn*k). In worst case, hash map takes O(n), but in average takes O(1)
n is the number of friend, m is average wish list each friend has
k is the size of my wish list
Space: O(k) for find buddy, O(k + mn) for recommend
We use a set to store out list, takes O(k)
for recommend, we use a linked hash set to store the recommend, which takes O(mn)
*/
public class CTravelBuddy {

    final double THRESHOLD = 0.0001;
    public static void main(String[] args) {

        CTravelBuddy sol = new CTravelBuddy();
        List<String> myList = Arrays.asList("A", "B", "C", "D");
        Map<String, List<String>> friendList = new HashMap<>();
        friendList.put("peter", Arrays.asList("A", "B", "E", "F"));
        friendList.put("john", Arrays.asList("A", "B", "D", "G"));
        friendList.put("casy", Arrays.asList("X", "B", "A", "D", "P", "Q"));
        friendList.put("jason", Arrays.asList("A", "B", "C", "D", "P"));
        friendList.put("ken", Arrays.asList("A", "X", "Y", "Z"));

        List<Buddy> res = sol.findBuddy(myList, friendList);
        for(Buddy buddy : res) {
            System.out.print(buddy.name + ": ");
            System.out.println(buddy.similarity);
        }
        List<String> recommend = sol.recommend(myList, friendList, 15);
        System.out.println(recommend);
    }

    public List<Buddy> findBuddy(List<String> myList, Map<String, List<String>> friendMap) {
        Set<String> mySet = new HashSet<>(myList);
        List<Buddy> res = new ArrayList<>();
        for (String friend : friendMap.keySet()) {
            List<String> friendList = friendMap.get(friend);
            int count = 0;
            for (String val : friendList) {
                if (mySet.contains(val)) count++;
            }
            double similarity = count * 1.0 / friendList.size();
            if (similarity >= 0.5) {
                res.add(new Buddy(friend, similarity));
            }
        }
//        Collections.sort(res, (o1, o2) -> {
//            return Double.compare(o2.similarity, o1.similarity);
//        });
        Collections.sort(res);
        return res;
    }

    public List<String> recommend(List<String> myList, Map<String, List<String>> friendMap,  int k) {

        Set<String> mySet = new HashSet<>(myList);
        List<Buddy> buddies = findBuddy(myList, friendMap);
        Set<String> recommend = new LinkedHashSet<>();  // order matters and we want uniq city

        for (Buddy buddy : buddies) {
            for (String city : friendMap.get(buddy.name)) {
                if (!mySet.contains(city)) {
                    if (k > 0) {
                        if (recommend.add(city)) {   // if same city, should not decrease k
                            k--;
                        }
                    }
                }
            }
        }
        return new ArrayList<>(recommend);
    }

    class Buddy implements Comparable<Buddy>{
        String name;
        double similarity;

        public Buddy(String name, double similarity) {
            this.name = name;
            this.similarity = similarity;
        }

        @Override
        public int compareTo(Buddy other) {
            if (Math.abs(this.similarity - other.similarity) <= THRESHOLD) {
                return this.name.compareTo(other.name);
            } else if (this.similarity < other.similarity) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}

