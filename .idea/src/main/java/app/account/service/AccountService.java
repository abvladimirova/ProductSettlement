package app.account.service;

import org.springframework.stereotype.Service;
import app.account.entity.Account;
import app.account.exceptions.AccountNotFoundException;
import app.account.exceptions.AccountPoolNotFound;

@Service
public interface AccountService {

    Account getAccountByParams(String branch, String currency, String mdmCode, String priority, String registryType) throws AccountPoolNotFound, AccountNotFoundException;

}
