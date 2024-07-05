package app.dictionaries.accountpool;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPoolRepo extends CrudRepository<AccountPool, Integer>{

    AccountPool findFirstByBranchAndCurrencyAndMdmCodeAndPriorityAndRegisterType(String branch, String currency, String mdmCode, String priority, String registryType);

}


