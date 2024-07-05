package app.account;

import app.common.exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.dictionaries.accountpool.AccountPool;
import app.dictionaries.accountpool.AccountPoolRepo;

import java.util.Objects;
/*
    Найти значение номера счета по параметрам
            branchCode,
            currencyCode,
            mdmCode,
            priorityCode,
            registryTypeCode
            из Request.Body в таблице Пулов счетов (account_pool). Номер счета берется первый из пула
*/

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepo accountRepo;
    private final AccountPoolRepo accountPoolRepo;

    private AccountPool getPool(String branch, String currency, String mdmCode, String priority, String registryType, boolean throwError)  throws NoDataFoundException {
        var pool = accountPoolRepo.findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(branch, currency, mdmCode, priority, registryType);

        if (Objects.isNull(pool))
            if (throwError)
            throw new NoDataFoundException("Не найден подходящий пул счетов по переданным параметрам");

        return pool;

    }

    private Account getAccountByPool(AccountPool pool, boolean throwError) throws NoDataFoundException {

        var account = accountRepo.findFirstByPoolAndBusyFalseOrderByAccountNumber(pool);

        if (Objects.isNull(account))
            if (throwError)
            throw new NoDataFoundException("Не найден счёт в соответствующем пуле счетов");

        return account;
    }

    @Override
    public Account getAccountByParams(String branch, String currency, String mdmCode, String priority, String registryType) throws NoDataFoundException {

        return getAccountByPool (getPool(branch, currency, mdmCode, priority, registryType, true), true);
    }

    public Account getAccountByParams(String branch, String currency, String mdmCode, String priority, String registryType, boolean throwError) {

        return getAccountByPool (getPool(branch, currency, mdmCode, priority, registryType, throwError), throwError);
    }
}
