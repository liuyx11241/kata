package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AddAmountActionTest {

    private AccountDto account;

    @Before
    public void setUp() throws Exception {
        account = new AccountDto("idAccount", "idHolder");
    }

    @Test
    public void doAction_0() {
        BigDecimal balance = BigDecimal.ZERO;
        account.setBalance(balance);

        BigDecimal amount = BigDecimal.valueOf(32);

        AddAmountAction action = new AddAmountAction(account, amount);
        action.doAction();

        Assert.assertEquals(account.getBalance(), balance.add(amount));
    }

    @Test
    public void doAction() {
        BigDecimal balance = BigDecimal.valueOf(90.32);
        account.setBalance(balance);

        BigDecimal amount = BigDecimal.valueOf(32);

        AddAmountAction action = new AddAmountAction(account, amount);
        action.doAction();

        Assert.assertEquals(account.getBalance(), balance.add(amount));
    }
}