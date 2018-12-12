package demo.bank.kata.action;

import org.junit.Test;

import java.math.BigDecimal;

public class CheckAmountActionTest {

    @Test(expected = IllegalArgumentException.class)
    public void doAction_null() {
        CheckAmountAction checkAmountAction = new CheckAmountAction(null);
        checkAmountAction.doAction();
    }

    @Test(expected = IllegalArgumentException.class)
    public void doAction_lt0() {
        CheckAmountAction checkAmountAction = new CheckAmountAction(BigDecimal.valueOf(-1));
        checkAmountAction.doAction();
    }

    @Test(expected = IllegalArgumentException.class)
    public void doAction_0() {
        CheckAmountAction checkAmountAction = new CheckAmountAction(BigDecimal.ZERO);
        checkAmountAction.doAction();
    }

    @Test
    public void doAction_gt0() {
        CheckAmountAction checkAmountAction = new CheckAmountAction(BigDecimal.TEN);
        checkAmountAction.doAction();
    }
}