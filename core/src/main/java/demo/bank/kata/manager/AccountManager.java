package demo.bank.kata.manager;

import demo.bank.kata.dto.AccountDto;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
public class AccountManager {

    private Map<String, AccountDto> repo;

    public AccountManager() {
        repo = new HashMap<>(2);
        repo.put("123456", new AccountDto("123456", "holder1", BigDecimal.ZERO));
        repo.put("654321", new AccountDto("654321", "holder2", BigDecimal.ZERO));
    }

    public AccountDto findByIdAccount(String idAccount) {
        // fixme : replace by JPA request
        if (repo.containsKey(idAccount)) {
            return repo.get(idAccount);
        }
        return null;
    }
}
