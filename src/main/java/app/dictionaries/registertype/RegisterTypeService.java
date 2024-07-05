package app.dictionaries.registertype;

import app.common.exceptions.NoDataFoundException;
import app.dictionaries.accounttype.AccountType;
import app.dictionaries.productclass.ProductClass;

import java.util.List;

public interface RegisterTypeService {
    ProductRegisterType findByCode(String code) throws NoDataFoundException;

    List<ProductRegisterType> findByClassAndAccountType(ProductClass productClass, AccountType accountType) throws NoDataFoundException;

}
