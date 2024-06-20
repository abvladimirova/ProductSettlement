package app.productregister.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.account.entity.Account;
import app.product.exceptions.ProductNotFoundException;
import app.product.repository.TppProductRepo;
import app.productregister.repository.TppProductRegisterRepo;
import app.productregister.entity.TppProductRegisterStates;
import app.productregister.entity.TppProductRegister;
import app.registertype.entity.ProductRegisterType;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class TppProductRegisterServiceImpl implements TppProductRegisterService {

    private final TppProductRegisterRepo registerRepo;
    private final TppProductRepo productRepo;

    @Override
    public TppProductRegister getProductRegister(BigInteger productId, ProductRegisterType registryType)
            throws ProductNotFoundException {
        var productOptional = productRepo.findById(productId.intValue());
        if (productOptional.isPresent()) {
            return registerRepo.findByProductAndType(productOptional.get(),registryType);
        }
        else throw new ProductNotFoundException("Не найден ЭП с id = " + productId);

    }

    @Override
    @Transactional
    public TppProductRegister newProductRegister(BigInteger product, ProductRegisterType type, String currency, Account account)
            throws ProductNotFoundException {
        var productOptional = productRepo.findById(product.intValue());
        if (productOptional.isPresent()) {
            var productRegister = new TppProductRegister(0, productOptional.get() ,type,currency, account.getAccountNumber(), BigInteger.valueOf(account.getId()), TppProductRegisterStates.OPEN.stateName);
            registerRepo.save(productRegister);
            return productRegister;
        }
        else throw new ProductNotFoundException("Не найден ЭП с id = " + product);

    }

}
