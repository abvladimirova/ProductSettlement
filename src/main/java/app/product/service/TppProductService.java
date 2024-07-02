package app.product.service;

import app.product.entity.TppProduct;
import app.product.entity.AdditionalProperty;
import app.product.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

public interface TppProductService {
    public TppProduct getProduct(Integer id) throws ProductNotFoundException;

    public TppProduct getProductByContractNumber (String ContractNumber);

    TppProduct newProduct(BigInteger productCodeId,
                          BigInteger clientId,
                          String type,
                          String number,
                          BigInteger priority,
                          Timestamp dateOfConclusion,
                          float penaltyRate,
                          BigDecimal nso,
                          BigDecimal thresholdAmount,
                          String interestRateType);
    //TppProduct addProperty(AdditionalProperty additionalProperty);
}
