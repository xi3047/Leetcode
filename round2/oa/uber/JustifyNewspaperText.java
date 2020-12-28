package round2.oa.uber;

import org.junit.Test;

/**
 * @author Xi Zhang
 * @date 12/24/2020 2:45 PM
 * @topic round2.oa.uber
 * @link
 * @description
 */
public class JustifyNewspaperText {
    public String[] justify(String[][] lines, String [] align, int width) {
        return null;
    }

    private String rightPad(String[] words, int width) {
        return null;
    }
    private String leftPad(String[] words, int width) {
        return null;
    }
    private String[] split(String[] words, int width) {
        return null;
    }

    @Test
    public void test() {
        String[][] lines = new String[][] {
                {"Hello", "World"},
                {"Please align", "to the right"},
                {"Something else", "is coming", "too"}
        };
        String[] align = new String[]{
                "LEFT", "RIGHT", "RIGHT"
        };
        String[] res = justify(lines, align, 16);
        for (String line : res) {
            System.out.println(line);
        }
    }
}
