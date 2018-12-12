package demo.bank.kata.manager;

import demo.bank.kata.action.IAction;
import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.exception.AccountNotFoundException;
import demo.bank.kata.exception.BalanceNotEnoughException;
import demo.bank.kata.operation.IOperation;
import demo.bank.kata.operation.OperationFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;

@Service
public class OperationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(OperationManager.class);

    @Autowired
    private AccountManager accountManager;

    @Autowired
    private OperationFactory operationFactory;

    // todo : add currency check/support
    public AccountDto deposit(String idAccount, BigDecimal amount) throws AccountNotFoundException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Start Deposit - Account {} Amount {}", idAccount, amount);
        }

        AccountDto account = accountManager.findByIdAccount(idAccount);
        if (account == null) {
            throw new AccountNotFoundException(idAccount);
        }
        this.executeOperation(operationFactory.buildDepositOperation(account, amount));
        return account;
    }

    public AccountDto deposit(String idAccount, double amount) throws AccountNotFoundException {
        return this.deposit(idAccount, BigDecimal.valueOf(amount));
    }

    public AccountDto deposit(String idAccount, long amount) throws AccountNotFoundException {
        return this.deposit(idAccount, BigDecimal.valueOf(amount));
    }

    // todo : add currency check/support
    public AccountDto withdraw(String idAccount, BigDecimal amount) throws AccountNotFoundException, BalanceNotEnoughException {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Start Withdraw - Account {} Amount {}", idAccount, amount);
        }

        AccountDto account = accountManager.findByIdAccount(idAccount);
        if (account == null) {
            throw new AccountNotFoundException(idAccount);
        }
        if (account.getBalance().compareTo(amount) < 0) {
            throw new BalanceNotEnoughException(idAccount);
        }
        this.executeOperation(operationFactory.buildWithdrawOperation(account, amount));
        return account;
    }

    public AccountDto withdraw(String idAccount, double amount) throws AccountNotFoundException, BalanceNotEnoughException {
        return this.withdraw(idAccount, BigDecimal.valueOf(amount));
    }

    public AccountDto withdraw(String idAccount, long amount) throws AccountNotFoundException, BalanceNotEnoughException {
        return this.withdraw(idAccount, BigDecimal.valueOf(amount));
    }

    private void executeOperation(IOperation operation) {
        if (null == operation || CollectionUtils.isEmpty(operation.getActionList())) {
            return;
        }

        operation.getActionList().forEach(IAction::doAction);
    }
}
