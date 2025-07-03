package round4.bdfs;

import java.util.ArrayList;
import java.util.List;

public class L320_GeneralizedAbbreviation {
    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();
        dfs(res, word, new StringBuilder(), 0, 0);
        return res;
    }

    private void dfs(List<String> res, String word, StringBuilder path, int index, int count) {
        if (index == word.length()) {
            if (count > 0) {
                path.append(count);
            }
            res.add(path.toString());
            return;
        }

        int len = path.length();
        // number
        dfs(res, word, path, index + 1, count + 1);
        path.setLength(len);
        // letter
        if (count > 0) {
            path.append(count);
        }
        path.append(word.charAt(index));
        dfs(res, word, path, index + 1, 0);
        path.setLength(len);
    }
}
