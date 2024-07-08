package app.productregister;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import app.product.TppProduct;
import app.dictionaries.registertype.ProductRegisterType;

@Repository
public interface TppProductRegisterRepo extends JpaRepository<TppProductRegister, Integer> {

    TppProductRegister findByProductAndType(TppProduct productId, ProductRegisterType type);

}
