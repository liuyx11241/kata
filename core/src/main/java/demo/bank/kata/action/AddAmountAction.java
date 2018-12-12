package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;

import java.math.BigDecimal;

public final class AddAmountAction extends AbstractBalanceAction {
    public AddAmountAction(AccountDto account, BigDecimal amount) {
        super(account, amount);
    }

    @Override
    public void doAction() {
        this.account.setBalance(this.account.getBalance().add(amount));
    }
}
