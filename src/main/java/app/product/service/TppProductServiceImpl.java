package app.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.product.entity.TppProduct;
import app.product.exceptions.ProductNotFoundException;
import app.product.repository.TppProductRepo;
import app.product.entity.AdditionalProperty;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TppProductServiceImpl implements TppProductService {
    TppProductRepo productRepo;

    public TppProductServiceImpl(@Autowired TppProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    @Override
    public TppProduct getProduct(Integer id) throws ProductNotFoundException {
        return productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException("Не найден ЭП с id = " + id));
    }

    @Override
    @Transactional
    public TppProduct newProduct(Integer id,
                                 BigInteger productCodeId,
                                 BigInteger clientId,
                                 String type,
                                 String number,
                                 BigInteger priority,
                                 Timestamp dateOfConclusion,
                                 float penaltyRate,
                                 BigDecimal nso,
                                 BigDecimal thresholdAmount,
                                 String interestRateType,
                                 List<AdditionalProperty> additionalProperties
    ) {

        var product = new TppProduct(
                id,
                productCodeId,
                clientId,
                type,
                number,
                priority/*,
            dateOfConclusion*/);

        product.setPenaltyRate(penaltyRate)
                .setNso(nso)
                .setThresholdAmount(thresholdAmount)
                .setInterestRateType(interestRateType);

        return productRepo.save(product);
         //product;
    }
}
