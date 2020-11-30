package round1_misc.amazon;

import org.junit.Test;

import java.util.*;

/*
    @author: Xi Zhang
    @date:   2019-02-24
    @time:   14:03
    Amazon OA question Q9
    找出电影中的每个片段的长度
 */
public class lengthEachScene {
    public static List<Integer> lengthEachScene(List<Character> inputList) {
        List<Integer> lengthEachScene = new ArrayList<>();
        // cc
        // map 的key是当前的shot， value是当前shot总共的次数
        Map<Character, Integer> map = new HashMap<>();
        // set代表的 一段scene里存于的shot
        Set<Character> set = new HashSet<>();

        // 把每个shot和出现的次数 放到map里
        for (char c : inputList) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        // 初始化当前片段的长度
        int curSceneLen = 0;

        // 无脑遍历全部的shot
        for (int i = 0; i < inputList.size(); i++) {
            // 片段长度无脑加一
            curSceneLen++;
            char cur = inputList.get(i);
            // 如果set里没有当前的shot， 加进去
            if (!set.contains(cur)) {
                set.add(cur);
            }
            // 从map里把当前的shot减去一次
            map.put(cur, map.get(cur) - 1);

            // 如果当前shot的已经全部出现了，把它从 目前的Scene set 里移除
            if (map.get(cur) == 0) {
                set.remove(cur);
            }
            // 如果当前的scene set 已不包含任何的shot， 说明一个scene 已拍摄完成，把它的长度加到结果列表里
            if (set.size() == 0) {
                lengthEachScene.add(curSceneLen);
                curSceneLen = 0;
            }
        }

        return lengthEachScene;
    }

    public static void main(String[] args) {
        List<Character> input = Arrays.asList('a','b','a','b','c','b','a','c','a','d','e','f','e','g','d','e','h','i','j','h','k','l','i','j');
        System.out.println(lengthEachScene(input));
    }
    @Test
    public void test() {
        List<Character> input = Arrays.asList('a','b','a','b','c','b','a','c','a','d','e','f','e','g','d','e','h','i','j','h','k','l','i','j');

    }

}
