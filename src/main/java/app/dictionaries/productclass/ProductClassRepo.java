package app.dictionaries.productclass;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductClassRepo extends CrudRepository<ProductClass, Integer> {
    ProductClass getFirstByValue(String code);

}
