package demo.bank.kata.action;

import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.dto.HistoryItemDto;
import demo.bank.kata.manager.HistoryItemManager;
import demo.bank.kata.operation.EnumTypeOperation;

import java.math.BigDecimal;

public class CreateHistoryItemAction extends AbstractBalanceAction {
    private HistoryItemManager historyItemManager;
    private EnumTypeOperation typeOperation;
    private String commentOperation;

    public CreateHistoryItemAction(HistoryItemManager historyItemManager,
                                   AccountDto account, BigDecimal amount,
                                   EnumTypeOperation typeOperation) {
        this(historyItemManager, account, amount, typeOperation, "");
    }

    public CreateHistoryItemAction(HistoryItemManager historyItemManager,
                                   AccountDto account, BigDecimal amount,
                                   EnumTypeOperation typeOperation, String commentOperation) {
        super(account, amount);
        this.historyItemManager = historyItemManager;
        this.typeOperation = typeOperation;
        this.commentOperation = commentOperation;
    }

    @Override
    public void doAction() {
        HistoryItemDto historyItemDto = new HistoryItemDto();
        historyItemDto.setIdAccount(account.getIdAccount());
        historyItemDto.setNewBalance(account.getBalance());
        historyItemDto.setDeltaBalance(amount);
        historyItemDto.setCommentOperation(commentOperation);
        historyItemDto.setTypeOperation(typeOperation);
        historyItemManager.saveHistoryItem(historyItemDto);
    }
}
