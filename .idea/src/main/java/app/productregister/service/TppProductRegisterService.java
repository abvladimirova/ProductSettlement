package app.productregister.service;

import app.account.entity.Account;
import app.product.exceptions.ProductNotFoundException;
import app.productregister.entity.TppProductRegister;
import app.registertype.entity.ProductRegisterType;

import java.math.BigInteger;

public interface TppProductRegisterService {
    TppProductRegister getProductRegister(BigInteger productId, ProductRegisterType registryType) throws ProductNotFoundException;

    TppProductRegister newProductRegister(BigInteger product, ProductRegisterType type, String currency, Account account) throws ProductNotFoundException;
}
