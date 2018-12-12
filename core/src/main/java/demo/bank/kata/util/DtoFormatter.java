package demo.bank.kata.util;

import demo.bank.kata.dto.AccountDto;
import demo.bank.kata.dto.HistoryItemDto;

public class DtoFormatter {
    private DtoFormatter() {
        // empty
    }

    public static String format(HistoryItemDto historyItem) {
        if (historyItem == null) {
            return "";
        }
        return String.format("%-32s|%32s|%16s|%16s|%+16.2f|%16.2f",
            historyItem.getTimeOperation(),
            historyItem.getIdAccount(),
            historyItem.getTypeOperation(),
            historyItem.getCommentOperation(),
            historyItem.getDeltaBalance(),
            historyItem.getNewBalance());
    }

    public static String format(AccountDto account) {
        if (account == null) {
            return "";
        }
        return String.format("%-32s|%32s|%+16.2f|%4s",
            account.getIdAccount(),
            account.getIdHolder(),
            account.getBalance(),
            account.getCurrency());

    }
}
