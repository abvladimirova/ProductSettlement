package app.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.product.TppProduct;

import java.math.BigInteger;


@Repository
public interface TppProductRepo extends CrudRepository<TppProduct, BigInteger> {
    TppProduct findFirstByNumber(String contractNumber);

}
