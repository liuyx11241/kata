package demo.bank.kata.dto;

import demo.bank.kata.util.DtoFormatter;

import java.math.BigDecimal;
import java.util.Currency;

public class AccountDto {
    private String idAccount;

    private String idHolder;

    private BigDecimal balance;

    private Currency currency;

    public AccountDto(String idAccount, String idHolder) {
        this(idAccount, idHolder, BigDecimal.ZERO, Currency.getInstance("EUR"));
    }

    public AccountDto(String idAccount, String idHolder, BigDecimal balance) {
        this(idAccount, idHolder, balance, Currency.getInstance("EUR"));
    }

    public AccountDto(String idAccount, String idHolder, BigDecimal balance, Currency currency) {
        assert idAccount != null : "id account is null";
        assert idHolder != null : "id holder is null";
        assert balance != null : "balance is null";
        assert currency != null : "currency is null";

        this.idAccount = idAccount;
        this.idHolder = idHolder;
        this.balance = balance;
        this.currency = currency;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public String getIdHolder() {
        return idHolder;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return DtoFormatter.format(this);
    }
}