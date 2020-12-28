package round2.oa;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Xi Zhang
 * @date 12/27/2020 12:27 AM
 * @topic round2.oa
 * @link
 * @description
 */
public class RobinhoodStock {

    private class Node {
        public int price;
        public int share;
        public Node(int price, int share) {
            this.price = price;
            this.share = share;
        }
    }

    private class BidComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return b.price - a.price;
        }
    }

    private class AskComparator implements Comparator<Node> {
        @Override
        public int compare(Node a, Node b) {
            return a.price - b.price;
        }
    }

     public int RobinhoodStock(String[][] orders) {
        if(orders == null || orders.length == 0 || orders[0].length == 0) {
            return 0;
        }
        int res = 0;
        PriorityQueue<Node> bidHeap = new PriorityQueue<Node>(new BidComparator());
        PriorityQueue<Node> askHeap = new PriorityQueue<Node>(new AskComparator());

        for(int i = 0; i < orders.length; i++) {
            int price = Integer.parseInt(orders[i][0]);
            int share = Integer.parseInt(orders[i][1]);

            if(orders[i][2].equals("buy")) {
                Node bid = new Node(price, share);
                while(!askHeap.isEmpty() && askHeap.peek().price <= bid.price && bid.share > 0) {
                    Node ask = askHeap.peek();
                    int transacted = Math.min(ask.share, bid.share);
                    res += transacted;
                    ask.share -= transacted;
                    bid.share -= transacted;
                    if (ask.share == 0) askHeap.poll();
                }
                if (bid.share > 0) bidHeap.offer(bid);

            } else {
                // “SELL” buy price >= sell price;
                Node ask = new Node(price, share);
                while(!bidHeap.isEmpty() && bidHeap.peek().price >= ask.price && ask.share > 0) {
                    Node bid = bidHeap.peek();
                    int transacted = Math.min(ask.share, bid.share);
                    res += transacted;
                    ask.share -= transacted;
                    bid.share -= transacted;
                    if (bid.share == 0) bidHeap.poll();
                }
                if(ask.share > 0) {
                    askHeap.offer(ask);
                }
            }
        }
        return res;
    }

    @Test
    public void test(){
        String[][] order = new String[][]{
                {"150", "5", "buy"},
                {"190", "1", "sell"},
                {"200", "1", "sell"},
                {"100", "9", "buy"},
                {"140", "8", "sell"},
                {"210", "4", "buy"}
        };
        System.out.println(RobinhoodStock(order));
    }


    /*


    def bad_transfer(src_account, dst_account, amount):
        try:
                if(src_account.id<dst_account.id) {
                        src_account.mutex.acquire();
                        dst_account.mutex.accquire();
                else {
                        dst_account.mutex.acquire();
                        src_account.mutex.acquire();
                }
                src_cash = src_account.cash # DB read
                dst_cash = dst_account.cash # DB read
                if src_cash < amount:
                        raise InsufficientFunds
                src_account.cash = src_cash - amount # DB write
                dst_account.cash = dst_cash + amount # DB write
                commit()
        except:
                rollback()
        else:
                src_account.mutex.release();
                dst_account.mutex.release();
                src_account.send_src_transfer_email()
                dst_account.send_dst_transfer_email()




     */

        /*
   price  share buy/sell
  ['150', '5', 'buy'],    # Order A   GONE
  ['190', '1', 'sell'],   # Order B
  ['200', '1', 'sell'],   # Order C
  ['100', '9', 'buy'],    # Order D
  ['140', '8', 'sell'],   # Order E 5
  ['210', '4', 'buy'],    # Order F 3 + 1

  output: 9;
*/

    //100, 140, 150,
//rule1: buy
}
