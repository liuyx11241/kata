package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;

import java.math.BigDecimal;

public final class ValidateBalanceAction extends AbstractAccountAction {

    public ValidateBalanceAction(AccountDto account) {
        super(account);
    }

    @Override
    public void doAction() {
        if (this.account.getBalance() == null || this.account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("exception.illegal.state.balance");
        }
    }
}
