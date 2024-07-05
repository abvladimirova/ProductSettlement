package app.dictionaries.accounttype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepo extends CrudRepository<AccountType, Integer> {
}
