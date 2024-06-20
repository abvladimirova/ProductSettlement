package app.registertype.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.registertype.entity.ProductRegisterType;

@Repository
public interface ProductRegisterTypeRepo extends CrudRepository<ProductRegisterType, Integer> {
    ProductRegisterType findByCode(String code);
}
