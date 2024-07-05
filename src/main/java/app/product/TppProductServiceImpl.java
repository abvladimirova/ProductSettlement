package app.product;

import app.common.exceptions.NoDataFoundException;
import app.dictionaries.CommonStates;
import app.dictionaries.productclass.ProductClass;
import app.dictionaries.AdditionalProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Service
public class TppProductServiceImpl implements TppProductService {
    TppProductRepo productRepo;

    public TppProductServiceImpl(@Autowired TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public TppProduct getProduct(BigInteger id) throws NoDataFoundException {
        return productRepo.findById(id).orElseThrow(() -> new NoDataFoundException("Не найден Экземпляр Продукта с id = " + id));
    }

    @Override
    public TppProduct getProductByContractNumber(String сontractNumber) {
        return productRepo.findFirstByNumber(сontractNumber);
    }

    @Override
    @Transactional
    public TppProduct newProduct(ProductClass productClass,
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
                                 float taxRate
    ) {

        var product = new TppProduct(
                BigInteger.valueOf(productClass.getId()) ,
                clientId,
                type,
                number,
                priority,
            dateOfConclusion);

        product.setPenaltyRate(penaltyRate)
                .setNso(nso)
                .setThresholdAmount(thresholdAmount)
                .setInterestRateType(interestRateType)
                .setTaxRate(taxRate)
                .setRequisiteType(requisiteType)
                .setState(CommonStates.OPEN.stateName);

        return productRepo.save(product);
         //product;
    }
    @Override
    public TppProduct addProperty(TppProduct product, AdditionalProperty additionalProperty){

        return product;
    }
}
