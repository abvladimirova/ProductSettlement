package app.productregister;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.product.TppProduct;
import app.dictionaries.registertype.ProductRegisterType;

@Repository
public interface TppProductRegisterRepo extends CrudRepository<TppProductRegister, Integer> {

    TppProductRegister findByProductAndType(TppProduct productId, ProductRegisterType type);

}
