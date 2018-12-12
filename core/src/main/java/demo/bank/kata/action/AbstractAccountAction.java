package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;

public abstract class AbstractAccountAction implements IAction {

    protected AccountDto account;

    protected AbstractAccountAction(AccountDto account) {
        if (account == null) {
            throw new IllegalArgumentException("exception.illegal.argument.account");
        }
        this.account = account;
    }
}
