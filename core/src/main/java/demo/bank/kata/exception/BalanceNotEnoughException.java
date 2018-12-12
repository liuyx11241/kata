package demo.bank.kata.exception;

public class BalanceNotEnoughException extends AccountException {
    public BalanceNotEnoughException(String idAccount) {
        super(idAccount);
    }

    public BalanceNotEnoughException(String idAccount, Throwable cause) {
        super(idAccount, cause);
    }

    public BalanceNotEnoughException(Throwable cause) {
        super(cause);
    }
}
