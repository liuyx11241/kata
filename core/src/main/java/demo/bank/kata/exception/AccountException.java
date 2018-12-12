package demo.bank.kata.exception;

public class AccountException extends Exception {
    public AccountException(String idAccount) {
        super(idAccount);
    }

    public AccountException(String idAccount, Throwable cause) {
        super(idAccount, cause);
    }

    public AccountException(Throwable cause) {
        super(cause);
    }
}
