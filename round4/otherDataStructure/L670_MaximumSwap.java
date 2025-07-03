package round4.otherDataStructure;

public class L670_MaximumSwap {
    public int maximumSwap(int num) {
        char[] arr = Integer.toString(num).toCharArray();
        int[] lastIndex = new int[10];

        for (int i = 0; i < arr.length; i++) {
            lastIndex[arr[i] - '0'] = i;
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 9; j > (arr[i] - '0'); j--) {
                if (lastIndex[j] > i) {
                    char temp = arr[i];
                    arr[i] = arr[lastIndex[j]];
                    arr[lastIndex[j]] = temp;
                    return Integer.parseInt(new String(arr));
                }
            }
        }
        return num;
    }
}
