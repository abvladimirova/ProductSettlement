package app.productregister.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.product.entity.TppProduct;
import app.productregister.entity.TppProductRegister;
import app.registertype.entity.ProductRegisterType;

@Repository
public interface TppProductRegisterRepo extends CrudRepository<TppProductRegister, Integer> {

    TppProductRegister findByProductAndType(TppProduct productId, ProductRegisterType type);

}
