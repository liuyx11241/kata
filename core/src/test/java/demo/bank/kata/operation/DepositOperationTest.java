package demo.bank.kata.operation;

import demo.bank.kata.action.AddAmountAction;
import demo.bank.kata.action.CheckAmountAction;
import demo.bank.kata.action.CreateHistoryItemAction;
import demo.bank.kata.dto.AccountDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DepositOperationTest {

    @Autowired
    OperationFactory operationFactory;

    @Test
    public void test_step() {
        IOperation operation =
            operationFactory.buildDepositOperation(new AccountDto("idAccount", "idHolder"), BigDecimal.ZERO);

        Assert.assertNotNull(operation.getActionList());
        Assert.assertEquals(3, operation.getActionList().size());
        Assert.assertTrue(operation.getActionList().get(0) instanceof CheckAmountAction);
        Assert.assertTrue(operation.getActionList().get(1) instanceof AddAmountAction);
        Assert.assertTrue(operation.getActionList().get(2) instanceof CreateHistoryItemAction);
    }
}