package demo.bank.kata.action;

import java.math.BigDecimal;

public final class CheckAmountAction implements IAction {

    private BigDecimal amount;

    public CheckAmountAction(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public void doAction() {
        if (this.amount == null || this.amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("exception.illegal.argument.amount");
        }
    }
}
