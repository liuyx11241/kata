package demo.bank.kata.operation;

import demo.bank.kata.action.*;
import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.manager.HistoryItemManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;

@Component
public class OperationFactory {

    @Autowired
    private HistoryItemManager historyItemManager;

    public IOperation buildDepositOperation(AccountDto account, BigDecimal amount) {
        return () -> Arrays.asList(
            new CheckAmountAction(amount),
            new AddAmountAction(account, amount),
            new CreateHistoryItemAction(historyItemManager, account, amount, EnumTypeOperation.DEPOSIT)
        );
    }

    public IOperation buildWithdrawOperation(AccountDto account, BigDecimal amount) {
        return () -> Arrays.asList(
            new CheckAmountAction(amount),
            new SubtractAmountAction(account, amount),
            new ValidateBalanceAction(account),
            new CreateHistoryItemAction(historyItemManager, account, amount.negate(), EnumTypeOperation.WITHDRAW)
        );
    }
}
