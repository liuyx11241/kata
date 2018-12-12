package demo.bank.kata.core;

import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.exception.AccountNotFoundException;
import demo.bank.kata.exception.BalanceNotEnoughException;
import demo.bank.kata.manager.HistoryItemManager;
import demo.bank.kata.manager.OperationManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoreApplicationTests {

    @Autowired
    OperationManager operationManager;

    @Autowired
    HistoryItemManager historyItemManager;

    @Test
    public void contextLoads() {
    }

    @Test
    public void scenario1() throws InterruptedException {
        try {
            AccountDto account = operationManager.deposit("123456", 20.43);
            Thread.sleep(1000);
            operationManager.withdraw("123456", 10);
            Thread.sleep(1000);
            operationManager.deposit("123456", 20);

            System.out.println();
            System.out.println();
            System.out.println(String.format("%-32s|%32s|%16s|%16s", "Time", "Account Number", "Operation", "Balance"));
            historyItemManager.findByAccount(account.getIdAccount()).forEach(System.out::println);
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        } catch (BalanceNotEnoughException e) {
            System.err.println(String.format("Balance Not Enough %s", e.getMessage()));
        }
    }

    @Test
    public void scenario2() {
        try {
            AccountDto account = operationManager.deposit("321654", 20.43);
            operationManager.withdraw("321654", 10);
            operationManager.deposit("321654", 20);
            System.out.println();
            System.out.println();
            System.out.println(String.format("%-32s|%32s|%16s|%16s|%16s|%16s", "Time", "Account Number", "Type", "Comments", "Operation", "Balance"));
            historyItemManager.findByAccount(account.getIdAccount()).forEach(System.out::println);
        } catch (AccountNotFoundException e) {
            System.err.println(String.format("Account Not Found %s", e.getMessage()));
        } catch (BalanceNotEnoughException e) {
            System.err.println(String.format("Balance Not Enough %s", e.getMessage()));
        }
    }

    @Test
    public void scenario3() {
        try {
            AccountDto account = operationManager.deposit("123456", 20.43);
            operationManager.withdraw("123456", 30);
            operationManager.deposit("123456", 20);
            System.out.println();
            System.out.println();
            System.out.println(String.format("%-32s|%32s|%16s|%16s|%16s|%16s", "Time", "Account Number", "Type", "Comments", "Operation", "Balance"));
            historyItemManager.findByAccount(account.getIdAccount()).forEach(System.out::println);
        } catch (AccountNotFoundException e) {
            System.err.println(String.format("Account Not Found %s", e.getMessage()));
        } catch (BalanceNotEnoughException e) {
            System.err.println(String.format("Balance Not Enough %s", e.getMessage()));
        }
    }
}
