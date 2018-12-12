package demo.bank.kata.manager;

import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.dto.HistoryItemDto;
import demo.bank.kata.exception.AccountNotFoundException;
import demo.bank.kata.exception.BalanceNotEnoughException;
import demo.bank.kata.operation.OperationFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

/**
 * @See {@link demo.bank.kata.action.AddAmountActionTest}
 * @See {@link demo.bank.kata.action.SubtractAmountActionTest}
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationManagerTest {

    @Spy
    @InjectMocks
    private OperationFactory operationFactory;
    @InjectMocks
    private OperationManager operationManager = new OperationManager();
    @Mock
    private AccountManager accountManager;
    @Mock
    private HistoryItemManager historyItemManager;

    private String accountZero = "noBalance";
    private String accountNormal = "123456";
    private String accountInexistant = "notFound";

    @Before
    public void setUp() throws Exception {
        operationFactory = Mockito.spy(new OperationFactory());
        MockitoAnnotations.initMocks(this);

        AccountDto accountZeroDto = new AccountDto(this.accountNormal, "idHolder");
        Mockito.when(accountManager.findByIdAccount(accountZero)).thenReturn(accountZeroDto);
        AccountDto accountNormalDto = new AccountDto(this.accountNormal, "idHolder", BigDecimal.TEN);
        Mockito.when(accountManager.findByIdAccount(this.accountNormal)).thenReturn(accountNormalDto);
        Mockito.when(accountManager.findByIdAccount(accountInexistant)).thenReturn(null);
    }

    @Test(expected = AccountNotFoundException.class)
    public void deposit_null() throws AccountNotFoundException {
        operationManager.deposit(null, BigDecimal.TEN);
    }

    @Test(expected = AccountNotFoundException.class)
    public void deposit_notFound() throws AccountNotFoundException {
        operationManager.deposit(accountInexistant, BigDecimal.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deposit_amountLt0() throws AccountNotFoundException {
        operationManager.deposit(accountNormal, BigDecimal.valueOf(-1));
    }

    @Test
    public void deposit() throws AccountNotFoundException {
        operationManager.deposit(accountNormal, BigDecimal.TEN);

        ArgumentCaptor<HistoryItemDto> historyItemDtoArgumentCaptor = ArgumentCaptor.forClass(HistoryItemDto.class);
        Mockito.verify(historyItemManager, Mockito.times(1))
            .saveHistoryItem(historyItemDtoArgumentCaptor.capture());

        HistoryItemDto historyItemDto = historyItemDtoArgumentCaptor.getValue();
        Assert.assertEquals(BigDecimal.TEN, historyItemDto.getDeltaBalance());
        Assert.assertEquals(BigDecimal.valueOf(20), historyItemDto.getNewBalance());
        Assert.assertEquals(accountNormal, historyItemDto.getIdAccount());
    }

    @Test(expected = AccountNotFoundException.class)
    public void withdraw_null() throws AccountNotFoundException, BalanceNotEnoughException {
        operationManager.withdraw(null, BigDecimal.TEN);
    }

    @Test(expected = AccountNotFoundException.class)
    public void withdraw_notFound() throws AccountNotFoundException, BalanceNotEnoughException {
        operationManager.withdraw(accountInexistant, BigDecimal.TEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void withdraw_amountLt0() throws AccountNotFoundException, BalanceNotEnoughException {
        operationManager.withdraw(accountNormal, BigDecimal.valueOf(-1));
    }

    @Test(expected = BalanceNotEnoughException.class)
    public void withdraw_balanceLt0() throws AccountNotFoundException, BalanceNotEnoughException {
        operationManager.withdraw(accountZero, BigDecimal.TEN);
    }

    @Test
    public void withdraw() throws AccountNotFoundException, BalanceNotEnoughException {
        operationManager.withdraw(accountNormal, BigDecimal.ONE);

        ArgumentCaptor<HistoryItemDto> historyItemDtoArgumentCaptor = ArgumentCaptor.forClass(HistoryItemDto.class);
        Mockito.verify(historyItemManager, Mockito.times(1))
            .saveHistoryItem(historyItemDtoArgumentCaptor.capture());

        HistoryItemDto historyItemDto = historyItemDtoArgumentCaptor.getValue();
        Assert.assertEquals(BigDecimal.ONE.negate(), historyItemDto.getDeltaBalance());
        Assert.assertEquals(BigDecimal.valueOf(9), historyItemDto.getNewBalance());
        Assert.assertEquals(accountNormal, historyItemDto.getIdAccount());
    }
}