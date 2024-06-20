package app.registertype.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import app.registertype.entity.ProductRegisterType;
import app.registertype.exceptions.RegisterTypeNotFoundException;
import app.registertype.repo.ProductRegisterTypeRepo;

import java.util.Objects;

@AllArgsConstructor
@Service
public class RegisterTypeServiceImpl implements RegisterTypeService{

    public final ProductRegisterTypeRepo repo;
    @Override
    public ProductRegisterType findByCode(String code) throws RegisterTypeNotFoundException {
        ProductRegisterType  type = repo.findByCode(code);
        if (Objects.isNull(type)) {
            throw new RegisterTypeNotFoundException("Код Продукта " + code + " не найден в Каталоге продуктов для данного типа Регистра");
        }
        return type;
    }
}
