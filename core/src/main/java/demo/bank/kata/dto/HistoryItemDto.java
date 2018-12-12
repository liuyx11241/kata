package demo.bank.kata.dto;

import demo.bank.kata.operation.EnumTypeOperation;
import demo.bank.kata.util.DateTimeFormatter;
import demo.bank.kata.util.DtoFormatter;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.UUID;

public class HistoryItemDto {
    private String idPrivate;

    private String idAccount;

    private EnumTypeOperation typeOperation;

    private String commentOperation;

    private BigDecimal newBalance;

    private BigDecimal deltaBalance;

    private String timeOperation;

    public HistoryItemDto() {
        this.idPrivate = UUID.randomUUID().toString().replaceAll("-", "");
        this.timeOperation = DateTimeFormatter.format(Calendar.getInstance());
    }

    public String getIdPrivate() {
        return idPrivate;
    }

    public String getTimeOperation() {
        return timeOperation;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public BigDecimal getDeltaBalance() {
        return deltaBalance;
    }

    public void setDeltaBalance(BigDecimal deltaBalance) {
        this.deltaBalance = deltaBalance;
    }

    public void setTypeOperation(EnumTypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public void setCommentOperation(String commentOperation) {
        this.commentOperation = commentOperation;
    }

    public EnumTypeOperation getTypeOperation() {
        return typeOperation;
    }

    public String getCommentOperation() {
        return commentOperation;
    }

    @Override
    public String toString() {
        return DtoFormatter.format(this);
    }
}
