package round2.oa.airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Xi Zhang
 * @date 1/21/2021 6:55 PM
 * @topic round2.bitsMath
 * @link
 * @description AirBnB Interview Question
 * Given a start IP address ip and a number of ips we need to cover n, return a representation of the range as a list
 * (of smallest possible length) of CIDR blocks.
 *
 * A CIDR block is a string consisting of an IP, followed by a slash, and then the prefix length. For example:
 * "123.45.67.89/20". That prefix length "20" represents the number of common prefix bits in the specified range.
 *
 * Example 1:
 * Input: ip = "255.0.0.7", n = 10
 * Output: ["255.0.0.7/32","255.0.0.8/29","255.0.0.16/32"]
 * Explanation:
 * The initial ip address, when converted to binary, looks like this (spaces added for clarity):
 * 255.0.0.7 -> 11111111 00000000 00000000 00000111
 * The address "255.0.0.7/32" specifies all addresses with a common prefix of 32 bits to the given address,
 * ie. just this one address.
 *
 * The address "255.0.0.8/29" specifies all addresses with a common prefix of 29 bits to the given address:
 * 255.0.0.8 -> 11111111 00000000 00000000 00001000
 * Addresses with common prefix of 29 bits are:
 * 11111111 00000000 00000000 00001000
 * 11111111 00000000 00000000 00001001
 * 11111111 00000000 00000000 00001010
 * 11111111 00000000 00000000 00001011
 * 11111111 00000000 00000000 00001100
 * 11111111 00000000 00000000 00001101
 * 11111111 00000000 00000000 00001110
 * 11111111 00000000 00000000 00001111
 *
 * The address "255.0.0.16/32" specifies all addresses with a common prefix of 32 bits to the given address,
 * ie. just 11111111 00000000 00000000 00010000.
 *
 * In total, the answer specifies the range of 10 ips starting with the address 255.0.0.7 .
 *
 * There were other representations, such as:
 * ["255.0.0.7/32","255.0.0.8/30", "255.0.0.12/30", "255.0.0.16/32"],
 * but our answer was the shortest possible.
 *
 * Also note that a representation beginning with say, "255.0.0.7/30" would be incorrect,
 * because it includes addresses like 255.0.0.4 = 11111111 00000000 00000000 00000100
 * that are outside the specified range.
 */
public class L751_IPtoCIDR {
    public List<String> ipToCIDR(String ip, int n) {
        int cur = toInt(ip);
        List<String> res = new ArrayList<>();
        while(n>0){
            int maxBits = Integer.numberOfTrailingZeros(cur);
            int maxAmount = 1<<maxBits;
            int bitVal = 1;
            int count = 0;
            while(bitVal< n && count< maxBits){
                bitVal<<=1;
                ++count;
            }
            if(bitVal>n){
                bitVal>>=1;
                --count;
            }
            res.add(toString(cur,32-count));
            n-= bitVal;
            cur+=(bitVal);
        }
        return res;
    }
    private String toString(int number, int range){
        //convert every 8 into an integer
        final int WORD_SIZE = 8;
        StringBuilder sb = new StringBuilder();
        for(int i=3; i>=0; --i){
            sb.append(Integer.toString(((number>>(i*WORD_SIZE))&255)));
            sb.append(".");
        }
        sb.setLength(sb.length()-1);
        sb.append("/");
        sb.append(Integer.toString(range));
        return sb.toString();
    }
    private int toInt(String ip){
        String [] sep = ip.split("\\.");
        int sum = 0;
        for(int i=0; i<sep.length;++i){
            sum*=256;
            sum+=Integer.parseInt(sep[i]);
        }
        return sum;
    }

    /**
     * Solution:
     * https://blog.csdn.net/qq_29051413/article/details/108575329
     */
    public List<String> ipToCIDR2 (String ip, int n) {
        long start = ipToLong(ip);
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            // lowestOneBit outputs the a number with only the rightmost 1 bit.
            // for example, 1010 0100 => 0000 0100
            // we choose a mask that covers the rightmost 1
            // for example 8 (1000) means we take 29 bits prefix
            // if n is smaller than 8, e.g. 2 (10), we can't take 29 prefix, we are limited to 31 prefix
            int mask = Math.max(33 - bitLength(lowestOneBit(start)), 33 - bitLength(n));
            ans.add(longToIP(start) + "/" + mask);
            // 1 << (32 - mask) means how many IP address the CIDR has consumed above
            start += 1 << (32 - mask);
            n -= 1 << (32 - mask);
        }
        return ans;
    }
    private long lowestOneBit(long n) {
        long ans = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                return 1 << ans;
            }
            ans++;
            n >>= 1;
        }
        return 0;
    }

    private long ipToLong(String ip) {
        long ans = 0;
        for (String x : ip.split("\\.")) {
            ans = ans * 256 + Integer.parseInt(x);
        }
        return ans;
    }

    private String longToIP(long x) {
        return String.format("%s.%s.%s.%s",
                x >> 24, (x >> 16) % 256, (x >> 8) % 256, x % 256);
    }
    // 二进制有效位数，x = 1010 0110, 输出 8, x =  0000 0000 输出1
    private int bitLength(long x) {
        if (x == 0) return 1;
        int ans = 0;
        while (x > 0) {
            x >>= 1;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        L751_IPtoCIDR sol = new L751_IPtoCIDR();
//        System.out.println(sol.ipToCIDR("255.0.0.7", 10));
        System.out.println(sol.ipToCIDR2("255.0.0.7", 10));
        //System.out.println(sol.lowestOneBit(24));
    }
}
