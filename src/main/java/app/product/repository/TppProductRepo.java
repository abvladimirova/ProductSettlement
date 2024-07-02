package app.product.repository;

import app.productregister.entity.TppProductRegister;
import app.registertype.entity.ProductRegisterType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.product.entity.TppProduct;


@Repository
public interface TppProductRepo extends CrudRepository<TppProduct, Integer> {
    TppProduct findFirstByNumber(String contractNumber);

}
