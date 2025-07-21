package round4.otherDataStructure;

import java.util.*;

public class EatingCake {
    /**
     * Snowflake tag
     * Given an integer array, 1 is person, 0 is empty space, 2 is cake
     * for every person find the cake closest to him
     * {0, 0, 1, 0, 1, 2 , 2}
     */
    public static Map<Integer, Integer> assignCakes(int[] arr) {
        List<Integer> people = new ArrayList<>();
        List<Integer> cakes = new ArrayList<>();
        Map<Integer, Integer> assigned = new HashMap<>();
        Set<Integer> usedPeople = new HashSet<>();

        // Collect indices of people and cakes
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) {
                people.add(i);
            } else if (arr[i] == 2) {
                cakes.add(i);
            }
        }

        // Assign each cake to the closest available person
        for (int cake : cakes) {
            int minDist = Integer.MAX_VALUE;
            int chosenPerson = -1;

            for (int person : people) {
                if (usedPeople.contains(person)) continue;

                int dist = Math.abs(cake - person);
                if (dist < minDist || (dist == minDist && person < chosenPerson)) {
                    minDist = dist;
                    chosenPerson = person;
                }
            }
            if (chosenPerson != -1) {
                assigned.put(chosenPerson, cake);
                usedPeople.add(chosenPerson);
            }
        }
        return assigned;
    }
}
