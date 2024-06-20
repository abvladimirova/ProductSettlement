package app.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.account.entity.Account;
import app.account.entity.AccountPool;

@Repository
public interface AccountRepo extends CrudRepository<Account, Integer> {
    Account findFirstByPoolOrderByAccountNumber(AccountPool pool);
}
