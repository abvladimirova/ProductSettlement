package app.productregister;

import app.common.exceptions.NoDataFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import app.account.Account;
import app.product.TppProductRepo;
import app.dictionaries.CommonStates;
import app.dictionaries.registertype.ProductRegisterType;

import java.math.BigInteger;

@Service
@AllArgsConstructor
public class TppProductRegisterServiceImpl implements TppProductRegisterService {

    private final TppProductRegisterRepo registerRepo;
    private final TppProductRepo productRepo;

    @Override
    public TppProductRegister getProductRegister(BigInteger productId, ProductRegisterType registryType)
            throws NoDataFoundException {
        var productOptional = productRepo.findById(productId);
        if (productOptional.isPresent()) {
            return registerRepo.findByProductAndType(productOptional.get(),registryType);
        }
        else throw new NoDataFoundException("Не найден ЭП с id = " + productId);

    }

    @Override
    @Transactional
    public TppProductRegister newProductRegister(BigInteger product, ProductRegisterType type, String currency, Account account)
            throws NoDataFoundException {
        var productOptional = productRepo.findById(product);
        if (productOptional.isPresent()) {
            var productRegister = new TppProductRegister(0, productOptional.get() ,type,currency, account.getAccountNumber(), BigInteger.valueOf(account.getId()), CommonStates.OPEN.stateName);
            account.setBusy(true);
            registerRepo.save(productRegister);
            return productRegister;
        }
        else throw new NoDataFoundException("Не найден ЭП с id = " + product);

    }

}
