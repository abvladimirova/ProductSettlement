package app.dictionaries.registertype;

import app.dictionaries.accounttype.AccountType;
import app.dictionaries.productclass.ProductClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRegisterTypeRepo extends CrudRepository<ProductRegisterType, Integer> {

    ProductRegisterType findByCode(String code);

    List<ProductRegisterType> findByProductClassAndAccountType(ProductClass productClass, AccountType accountType);
}
