package app.dictionaries.registertype;

import app.common.exceptions.NoDataFoundException;
import app.dictionaries.accounttype.AccountType;
import app.dictionaries.productclass.ProductClass;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class RegisterTypeServiceImpl implements RegisterTypeService{

    private final ProductRegisterTypeRepo repo;

    @Override
    public ProductRegisterType findByCode(String code) throws NoDataFoundException {
        ProductRegisterType  type = repo.findByCode(code);
        if (Objects.isNull(type)) {
            throw new NoDataFoundException("Тип регистра " + code + " не найден");
        }
        return type;
    }

    @Override
    public List<ProductRegisterType> findByClassAndAccountType(ProductClass productClass, AccountType accountType) throws NoDataFoundException {
        return repo.findByProductClassAndAccountType(productClass, accountType);
    }
}
