package app.account;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.account.Account;
import app.dictionaries.accountpool.AccountPool;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
    Account findFirstByPoolAndBusyFalseOrderByAccountNumber(AccountPool pool);
}
