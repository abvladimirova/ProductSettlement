package app.dictionaries.productclass;

import app.common.exceptions.NoDataFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ProductClassServiceImpl implements ProductClassService{
    @Autowired
    private ProductClassRepo repo;
    public ProductClass findByCode(String code){
        ProductClass productClass = repo.getFirstByValue(code);
        if (Objects.isNull(productClass)) {
            throw new NoDataFoundException("Код Продукта " + code + " не найден в Каталоге продуктов");
        }
        return productClass;
    }
}
