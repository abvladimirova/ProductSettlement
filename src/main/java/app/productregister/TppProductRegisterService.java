package app.productregister;

import app.account.Account;
import app.common.exceptions.NoDataFoundException;
import app.dictionaries.registertype.ProductRegisterType;

import java.math.BigInteger;

public interface TppProductRegisterService {
    TppProductRegister getProductRegister(BigInteger productId, ProductRegisterType registryType) throws NoDataFoundException;

    TppProductRegister newProductRegister(BigInteger product, ProductRegisterType type, String currency, Account account) throws NoDataFoundException;
}
