package round2.oa.pureStorage;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 1/23/2021 10:23 AM
 * @topic round2.oa.pureStorage
 * @link
 * @description Pure Storage OA question
 */
public class RacingResults {

    /*
     * Complete the 'print_classification' function below.
     *
     * The function accepts 2D_INTEGER_ARRAY raw_data as parameter.
     */

    public static void print_classification(List<List<Integer>> raw_data) {
        Map<Integer, Integer> pointMap = new HashMap<>();
        pointMap.put(1, 10);
        pointMap.put(2, 6);
        pointMap.put(3, 4);
        pointMap.put(4, 3);
        pointMap.put(5, 2);
        pointMap.put(6, 1);

        Map<Integer, Integer> racerToPoints = new HashMap<>();
        Map<Integer, Integer> racerToRaces = new HashMap<>();
        for (List<Integer> record : raw_data) {
            int racer = record.get(1);
            int position = record.get(2);
            int points = pointMap.getOrDefault(position, 0);

            racerToPoints.put(racer, racerToPoints.getOrDefault(racer, 0) + points);
            racerToRaces.put(racer, racerToRaces.getOrDefault(racer, 0) + 1);
        }
        List<Racer> racerList = new ArrayList<>();
        for (int racer : racerToPoints.keySet()) {
            Racer newRacer = new Racer(racer, racerToRaces.get(racer), racerToPoints.get(racer));
            racerList.add(newRacer);
        }
        Collections.sort(racerList);
        Racer winner = racerList.get(0);
        System.out.println(winner.racer_name + " " + winner.points);

    }
    static class Racer implements Comparable<Racer>{
        int racer_name;
        int races;
        int points;

        public Racer (int racer_name, int races, int points) {
            this.racer_name = racer_name;
            this.races = races;
            this.points = points;
        }

        @Override
        public int compareTo(Racer other) {
            if (this.points == other.points) {
                return this.races - other.races;
            } else {
                return other.points - this.points;
            }
        }
    }

}
