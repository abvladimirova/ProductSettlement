package app.product;

import app.common.exceptions.NoDataFoundException;
import app.dictionaries.productclass.ProductClass;
import app.dictionaries.AdditionalProperty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;


public interface TppProductService {
    TppProduct getProduct(BigInteger id) throws NoDataFoundException;

    TppProduct getProductByContractNumber(String ContractNumber);

    TppProduct newProduct(ProductClass productClass,
                          BigInteger clientId,
                          String type,
                          String number,
                          BigInteger priority,
                          Date dateOfConclusion,
                          float penaltyRate,
                          BigDecimal nso,
                          BigDecimal thresholdAmount,
                          String requisiteType,
                          String interestRateType,
                          float taxRate);
}
