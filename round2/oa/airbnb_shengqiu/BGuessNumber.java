package round2.oa.airbnb_shengqiu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
/*
Assumption
1. Given an API we can call, API takes a String from "1111" ~ "6666".
returns how many number is a match, correct position and value
2. Use this api to guess a number with as fewer API calls as possible

Approach:
1. So, we can first wrap the socket connection to a Connection class, then we will
use it to call the server.
2. We can first guess "1111", this way, we know how many 1 in the result.
we guess "2222" and we can know how many 2 in the result. and
so on. After this, for example, we know 1,2,2,4 are in the result, but we do not
know the order yet.
3. To get the order, we first fill an char array with the not exist
number, let's say 3. [3,3,3,3]. Then replace with [1,3,3,3], [3,1,3,3], [3,3,1,3] and
call api again ,we will know the position of 1. We do the same for the rest.

Time: at most 12 times of API calls
6+3+2+1 = 12
 */
public class BGuessNumber {

    public static void main(String[] args) {
        SocketClient client = new SocketClient("localhost", 8080);
        String[] pre = {"", "1111", "2222", "3333", "4444", "5555", "6666"};
        Queue<Character> exist = new LinkedList<>();
        char notExist = '0';
        // pre-process
        for (int i = 1; i < pre.length; i++) {
            client.sendRequest(pre[i]);
            int match = client.readResponse().charAt(0) - '0';
            if (match == 0) {
                notExist = (char)(i + '0');
            }
            for (int j = 0; j < match; j++) {
                exist.offer((char)(i + '0'));
            }
        }
        // we already know which number exist and the count of it, so we now need to know the position
        char[] res = new char[4];
        Arrays.fill(res, notExist);
        int count = 0;     // number of character complete
        while (!exist.isEmpty()) {
            int countPos = exist.size();
            char c = exist.poll();
            for (int i = 0; i < res.length; i++) {
                if (res[i] == notExist) {
                    if (countPos == 1) { // if the last position, we do not need call api, just add to result
                        res[i] = c;
                        count++;
                        break;
                    }
                    res[i] = c;
                    client.sendRequest(new String(res));
                    if (client.readResponse().charAt(0) - '0' > count) {
                        count++;
                        break;
                    }
                    res[i] = notExist;
                    countPos--;
                }
            }
        }
        System.out.println(new String(res));
    }
}

class SocketClient {

    PrintWriter out;
    BufferedReader in;

    public SocketClient(String host, int port) {
        try {
            Socket socket = new Socket(host, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendRequest(String req) {
        out.println(req);
    }

    public String readResponse() {
        try {
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Fake API: for test only
//    public String guessAPI(String guess) {
//        int match = 0;
//        int exist = 0;
//        for (int i = 0; i < guess.length(); i++) {
//            if (guess.charAt(i) == secret.charAt(i)) {
//                match++;
//            }
//            if (secret.indexOf(guess.charAt(i)) != -1) {
//                exist++;
//            }
//        }
//        return "" + match + exist;
//    }
}