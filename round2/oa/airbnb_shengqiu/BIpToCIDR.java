package round2.oa.airbnb_shengqiu;

import java.util.ArrayList;
import java.util.List;

/*
Assumption:
1. input is a ip string, and a integer k represents how many ip we want to cover
2. return a list of CIDR that can cover these ips
3. the given ip is a valid ip address, k is in the valid range

Approach:
for example:  x.x.x.8/30
x.x.x.00001000, if we cover the the first 30 bit, we will have
x.x.x.00001000
x.x.x.00001001
x.x.x.00001010
x.x.x.00001011
therefore, we can have 4 ips.

In detail, in this case, we want cover 5. we first transfer the string ip to a long value, then
we find the position of last bit which is set. Decide the position to cover, if we cover 29, that will
give us 8 ips which is too many. So we cover 30, give the above 4 ips. Then we add x.x.x.8/30 to result
and k = k - 4 = 1. x.x.x.8 + 4 = x.x.x.12

Time: O(k*l) = O(k) where l is the length of string ip, which is constant, k is at most 2^32
Space: O(l) for stringbuilder
*/
class BIpToCIDR {
    public static void main(String[] args) {
        BIpToCIDR sol = new BIpToCIDR();
//        String ip = "255.255.255.255";
//        System.out.println(sol.ipToCIDR(ip, 10));
        String ip2 = "255.0.0.7";
        System.out.println(sol.ipToCIDR(ip2, 10));
    }

    public List<String> ipToCIDR(String ip, int k) {
        List<String> res = new ArrayList<>();
        String[] ipSec = ip.split("\\.");
        long ipVal = 0;
        long upperBound = 0;
        for (String val : ipSec) {
            upperBound = upperBound * 256 + 255;
            ipVal = ipVal * 256 + Integer.parseInt(val);
        }
        while (k > 0 && ipVal <= upperBound) {
            long steps = ipVal & -ipVal;
            while (steps > k) steps >>= 1;
            res.add(getICDR(ipVal, steps));
            ipVal += steps;
            k -= steps;
        }
        return res;
    }

    private String getICDR(long ipVal, long steps) {
        StringBuilder sb = new StringBuilder();
        sb.append((ipVal >>> 24) & 0xFF);
        sb.append(".");
        sb.append((ipVal >>> 16) & 0xFF);
        sb.append(".");
        sb.append((ipVal >>> 8) & 0xFF);
        sb.append(".");
        sb.append(ipVal & 0xFF);
        int common = 33;
        while (steps > 0) {  // if steps = 4, common = 30, which is correct
            common--;
            steps /= 2;
        }
        sb.append("/");
        sb.append(common);
        return sb.toString();
    }
}

