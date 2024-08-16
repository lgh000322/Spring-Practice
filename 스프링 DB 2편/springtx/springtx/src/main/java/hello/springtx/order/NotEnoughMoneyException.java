package hello.springtx.order;

public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String msg) {
        super(msg);
    }

}
