package app.product;

import app.common.exceptions.NoDataFoundException;
import app.common.CommonStates;
import app.dictionaries.productclass.ProductClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Service
public class TppProductServiceImpl implements TppProductService {
    final private TppProductRepo productRepo;

    public TppProductServiceImpl(@Autowired TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public TppProduct getProduct(BigInteger id) throws NoDataFoundException {
        return productRepo.findById(id).orElseThrow(() -> new NoDataFoundException("Не найден Экземпляр Продукта с id = " + id));
    }

    @Override
    public TppProduct getProductByContractNumber(String contractNumber) {
        return productRepo.findFirstByNumber(contractNumber);
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

        var product = new TppProduct()
                .setProductCodeId(BigInteger.valueOf(productClass.getId()))
                .setClientId(clientId)
                .setProductType(type)
                .setNumber(number)
                .setPriority(priority)
                .setDateOfConclusion(dateOfConclusion)
                .setPenaltyRate(penaltyRate)
                .setNso(nso)
                .setThresholdAmount(thresholdAmount)
                .setInterestRateType(interestRateType)
                .setTaxRate(taxRate)
                .setRequisiteType(requisiteType)
                .setState(CommonStates.OPEN.stateName);

        return productRepo.save(product);

    }

}
