package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.dto.HistoryItemDto;
import demo.bank.kata.manager.HistoryItemManager;
import demo.bank.kata.operation.EnumTypeOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

@RunWith(MockitoJUnitRunner.class)
public class CreateHistoryItemActionTest {

    @Mock
    HistoryItemManager historyItemManager;

    private AccountDto account;

    @Before
    public void setUp() throws Exception {
        account = new AccountDto("idAccount", "idHolder");
    }

    @Test
    public void doAction_gt0() {
        CreateHistoryItemAction action = new CreateHistoryItemAction(historyItemManager, account, BigDecimal.TEN, EnumTypeOperation.DEPOSIT);
        action.doAction();

        ArgumentCaptor<HistoryItemDto> historyItemDtoArgumentCaptor = ArgumentCaptor.forClass(HistoryItemDto.class);
        Mockito.verify(historyItemManager, Mockito.times(1))
            .saveHistoryItem(historyItemDtoArgumentCaptor.capture());

        HistoryItemDto historyItemDto = historyItemDtoArgumentCaptor.getValue();
        Assert.assertEquals(BigDecimal.TEN, historyItemDto.getDeltaBalance());
        Assert.assertEquals(BigDecimal.ZERO, historyItemDto.getNewBalance());
        Assert.assertEquals(account.getIdAccount(), historyItemDto.getIdAccount());
        Assert.assertEquals(EnumTypeOperation.DEPOSIT, historyItemDto.getTypeOperation());
    }

    @Test
    public void doAction_lt0() {
        CreateHistoryItemAction action = new CreateHistoryItemAction(historyItemManager, account, BigDecimal.TEN.negate(), EnumTypeOperation.WITHDRAW);
        action.doAction();

        ArgumentCaptor<HistoryItemDto> historyItemDtoArgumentCaptor = ArgumentCaptor.forClass(HistoryItemDto.class);
        Mockito.verify(historyItemManager, Mockito.times(1))
            .saveHistoryItem(historyItemDtoArgumentCaptor.capture());

        HistoryItemDto historyItemDto = historyItemDtoArgumentCaptor.getValue();
        Assert.assertEquals(BigDecimal.TEN.negate(), historyItemDto.getDeltaBalance());
        Assert.assertEquals(BigDecimal.ZERO, historyItemDto.getNewBalance());
        Assert.assertEquals(account.getIdAccount(), historyItemDto.getIdAccount());
        Assert.assertEquals(EnumTypeOperation.WITHDRAW, historyItemDto.getTypeOperation());
    }
}