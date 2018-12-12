package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ValidateBalanceActionTest {

    private AccountDto accountDto;

    @Before
    public void setUp() throws Exception {
        accountDto = new AccountDto("idAccount", "idHolder");
    }

    @Test(expected = IllegalStateException.class)
    public void doAction_null() {
        accountDto.setBalance(null);
        ValidateBalanceAction action = new ValidateBalanceAction(accountDto);
        action.doAction();
    }

    @Test(expected = IllegalStateException.class)
    public void doAction_lt0() {
        accountDto.setBalance(BigDecimal.valueOf(-1));
        ValidateBalanceAction action = new ValidateBalanceAction(accountDto);
        action.doAction();
    }

    @Test
    public void doAction_0() {
        accountDto.setBalance(BigDecimal.ZERO);
        ValidateBalanceAction action = new ValidateBalanceAction(accountDto);
        action.doAction();
    }

    @Test
    public void doAction_gt0() {
        accountDto.setBalance(BigDecimal.TEN);
        ValidateBalanceAction action = new ValidateBalanceAction(accountDto);
        action.doAction();
    }
}