package app.account.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.account.entity.AccountPool;

@Repository
public interface AccountPoolRepo extends CrudRepository<AccountPool, Integer>{

    AccountPool findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(String branch, String currency, String mdmCode, String priority, String registryType);

}


