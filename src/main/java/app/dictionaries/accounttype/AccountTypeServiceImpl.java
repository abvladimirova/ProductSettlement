package app.dictionaries.accounttype;

import app.common.exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AccountTypeServiceImpl implements AccountTypeService {
    @Autowired
    private AccountTypeRepo repo;
    public static HashMap<String, AccountType> accountTypes;

    public AccountType getAccountType (String accountTypeCode) {
        init();
        AccountType accountType = accountTypes.get(accountTypeCode);
        if (Objects.isNull(accountType))
            throw new NoDataFoundException("Не найден тип счёта с кодом " + accountTypeCode);
        return accountType;
    }

    public void init() {
        var all = repo.findAll();
        accountTypes = StreamSupport.stream(all.spliterator(), false).collect(Collectors.toMap(a -> a.getValue(), a -> a, (prev, next) -> next, HashMap::new));
    }

}
