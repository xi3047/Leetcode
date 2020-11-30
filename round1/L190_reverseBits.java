package round1;


public class L190_reverseBits {
    public int reverseBits(int n) {
        if (n == Integer.MAX_VALUE || n == 0) {
            return n;
        }
        int result = 0;
        for (int i = 0;  i< 32 ; i++) {
            int temp = (n >> i) & 1;
            result |= temp << ( 31 -i);
        }
        return result;

    }

    public int reverseBits2(int n) {
        if (n == Integer.MAX_VALUE || n == 0) {
            return n;
        }
        for (int i = 0; i < 16; i++) {
            int left = 1 & n >> (31 - i);
            int right = 1 & n >> i;
            if (left != right) {
                n ^= 1 << (31 -i);
                n ^= 1 << i;
            }
        }
        return n;
    }

    public long reverseBits3(int n) {
        int res = 0;
        while (n != 0) {
            res = res* 2 + n % 2;
            n /= 2;
        }
        return res;
    }
}
