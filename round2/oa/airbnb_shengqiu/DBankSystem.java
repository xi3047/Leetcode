package round2.oa.airbnb_shengqiu;

import java.util.*;
/*
Assumption:
1. deposit: if user does not exist, create a new one and deposit
2. timestamp is passed in manually
3. check given two time stamp, only return the first and last balance
4. Assume the timestamp is increasing and >= 0 (true for real life) !!!!
5. Assume we use integer to represents user, integer to represents the balance

Approach:
1. First we want each user id map to one user's all transaction. Therefore we can use a HashMap to
with user id as key, and transaction as value. The transaction are timestamp, current balance pairs.
Each timestamp map to a balance, and we want the record sorted based on timestamp, therefore, a TreeMap
can be used to store the transaction.

Otherwise, we use a list of State to store the transaction, we just make sure every time we add a new record,
the timestamp are in ascending order.

Time: O(logN)
TreeMap implemented as a Red Black tree, which has time complexity O(logN) for insert, and lookup
 */

// Use TreeMap
public class DBankSystem {
    Map<Integer, TreeMap<Long, Integer>> account;

    public DBankSystem() {
        this.account = new HashMap<>();
    }

    public void deposit(int id, int amount, long timestamp) {
        account.putIfAbsent(id, new TreeMap<>());
        if (account.get(id).isEmpty()) {
            account.get(id).put(0L, 0);
        }
        account.get(id).put(timestamp, account.get(id).lastEntry().getValue() + amount);
    }

    public boolean withdraw(int id, int amount, long timestamp) {
        if (!account.containsKey(id) || account.get(id).lastEntry().getValue() < amount) {
            return false;
        }
        account.get(id).put(timestamp, account.get(id).lastEntry().getValue() - amount);
        return true;
    }

    public Integer check(int id, long startTime, long endTime) {
        if (!account.containsKey(id) || startTime > endTime) {
            return null;
        }
        TreeMap<Long, Integer> map = account.get(id);
        Long t1 = map.floorKey(startTime);
        Long t2 = map.floorKey(endTime);
        if (t1 == null || t2 == null) {
            return null;
        }
        return map.get(t2) - map.get(t1);
    }

    public static void main(String[] args) {
        DBankSystem cache = new DBankSystem();
        cache.deposit(1, 5, 1000);
        cache.deposit(1, 3, 2000);
        cache.deposit(1, 2, 3000);
        System.out.println("Cnt between " + 0 + " and " + 1500 + " is " + cache.check(1, 0, 1500));
        //first check our hit function

        System.out.println("Cnt between " + 1000 + " and " + 2000 + " is " + cache.check(1, 1000, 2000));

        System.out.println("Cnt between " + 0 + " and " + 100000 + " is " + cache.check(1, 0, 100000));

        System.out.println("Cnt between " + 1000 + " and " + 3000 + " is " + cache.check(1, 1000, 3000));

        System.out.println("Cnt between " + 0 + " and " + 3000 + " is " + cache.check(1, 0, 3000));

        System.out.println("Cnt between " + 4000 + " and " + 5000 + " is " + cache.check(1, 4000, 5000));
    }
}

// Use List of State
class BankSystemV2 {

    Map<Integer, List<State>> account;

    public BankSystemV2() {
        this.account = new HashMap<>();
    }

    public void deposit(int id, int amount, long timestamp) {
        account.putIfAbsent(id, new ArrayList<>());
        List<State> list = account.get(id);
        if (list.isEmpty()) {
            list.add(new State(0L, 0));
        }
        list.add(new State(timestamp, list.get(list.size() - 1).balance + amount));
    }

    public boolean withdraw(int id, int amount, long timestamp) {
        if (!account.containsKey(id)) {
            return false;
        }
        List<State> list = account.get(id);
        if (list.get(list.size() - 1).balance < amount) {
            return false;
        }
        list.add(new State(timestamp, list.get(list.size() - 1).balance - amount));
        return true;
    }

    public Integer check(int id, long startTime, long endTime) {
        if (!account.containsKey(id) || startTime > endTime) {
            return null;
        }
        List<State> list = account.get(id);
        State s1 = search(list, startTime);
        State s2 = search(list, endTime);
        if (s1 == null || s2 == null) {
            return null;
        }
        return s2.balance - s1.balance;
    }

    // search the largest timestamp smaller than or equal to given timestamp
    private State search(List<State> list,  long timeStamp) {
        int left = 0;
        int right = list.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).timeStamp <= timeStamp) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (list.get(right).timeStamp <= timeStamp) {
            return list.get(right);
        }
        if (list.get(left).timeStamp <= timeStamp) {
            return list.get(left);
        }
        return null;
    }

    class State {
        long timeStamp;
        int balance;

        public State(long timeStamp, int balance) {
            this.timeStamp = timeStamp;
            this.balance = balance;
        }
    }
}
