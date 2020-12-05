package round1;

import org.junit.Test;

/*
    @author: Xi Zhang
    @date:   2019-05-10
    @time:   11:02
 */
public class L12_IntegerToRoman {
    public String intToRoman(int num) {
        if (num < 1 || num > 3999) return "";

        StringBuilder result = new StringBuilder();

        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

        int i = 0;
        //iterate until the number becomes zero, NO NEED to go until the last element in roman array
        while (num >0) {
            while ( num >= values[i]) {
                num -= values[i];
                result.append(roman[i]);
            }
            i++;
        }

        return result.toString();
    }

    @Test
    public void test(){
        System.out.println(intToRoman(18));
    }

}
