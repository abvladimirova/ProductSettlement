package app.account.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.account.repository.AccountRepo;
import app.account.entity.Account;
import app.account.entity.AccountPool;
import app.account.exceptions.AccountNotFoundException;
import app.account.exceptions.AccountPoolNotFound;
import app.account.repository.AccountPoolRepo;

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

    private AccountPool getPool(String branch, String currency, String mdmCode, String priority, String registryType)  throws AccountPoolNotFound {
        var pool = accountPoolRepo.findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(branch, currency, mdmCode, priority, registryType);

        if (Objects.isNull(pool))
            throw new AccountPoolNotFound("Не найден подходящий пул счетов по переданным параметрам");

        return pool;

    }

    private Account getAccountByPool(AccountPool pool) throws AccountNotFoundException {

        var account = accountRepo.findFirstByPoolOrderByAccountNumber(pool);

        if (Objects.isNull(account))
            throw new AccountNotFoundException("Не найден счёт в соответствующем пуле счетов");

        return account;
    }

    @Override
    public Account getAccountByParams(String branch, String currency, String mdmCode, String priority, String registryType) throws AccountPoolNotFound, AccountNotFoundException {

        return getAccountByPool (getPool(branch, currency, mdmCode, priority, registryType));
    }
}
