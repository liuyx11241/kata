package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;

import java.math.BigDecimal;

public abstract class AbstractBalanceAction extends AbstractAccountAction {

    protected BigDecimal amount;

    protected AbstractBalanceAction(AccountDto account, BigDecimal amount) {
        super(account);
        if (amount == null) {
            throw new IllegalArgumentException("exception.illegal.argument.amount");
        }
        this.amount = amount;
    }
}
