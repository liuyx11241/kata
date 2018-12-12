package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;

import java.math.BigDecimal;

public final class SubtractAmountAction extends AbstractBalanceAction {
    public SubtractAmountAction(AccountDto account, BigDecimal amount) {
        super(account, amount);
    }

    @Override
    public void doAction() {
        this.account.setBalance(this.account.getBalance().subtract(amount));
    }
}
