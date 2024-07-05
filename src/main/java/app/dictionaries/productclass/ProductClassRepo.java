package app.dictionaries.productclass;

import app.dictionaries.productclass.ProductClass;
import org.springframework.data.repository.CrudRepository;

public interface ProductClassRepo extends CrudRepository<ProductClass, Integer> {
    ProductClass getFirstByValue(String code);

}
