package round4.array;

public class L2303_CalculateAmountPaidInTaxes {
    public static double calculateTax(int[][] brackets, int income) {
        double res = 0;
        double prevBound = 0;
        for (int[] curBracket : brackets) {
            if (income > curBracket[0]) {
                res += (curBracket[1] / 100.0) * (curBracket[0] - prevBound);
            } else {
                res += (curBracket[1] / 100.0) * (income - prevBound);
                break;
            }
            prevBound = curBracket[0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] brackets = {{1, 0}, {4, 25}, {5, 50}};
        System.out.println(calculateTax(brackets, 2));
    }
}
