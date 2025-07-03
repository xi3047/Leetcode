package round4.array;

public class L1295_FindNumbersWithEvenDigit {
    public int findNumbers(int[] nums) {
        int count = 0;
        for (int num : nums) {
            int divideCount = 0;
            while (num > 0) {
                num =  num / 10;
                divideCount++;
            }
            if (divideCount % 2 == 0) count++;
        }
        return count;
    }
}
