package OOD.vendingMachine;

public class vendingMachine {



    public vendingMachine(int itemID, Payment payment) {
        Item item = db.getItemByID(itemID);
        int value = 0;
        if (payment.getType() == Type.COIN) {
            value = payment.getCoinValue();
        } else if (payment.getType() == Type.CASH) {
            value = payment.getCashValue();
        }
        if (value > item.price) {
            // pay

        }

    }

    boolean purchase() {

    }
}
