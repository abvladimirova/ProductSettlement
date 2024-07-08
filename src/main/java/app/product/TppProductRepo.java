package app.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;


@Repository
public interface TppProductRepo extends JpaRepository<TppProduct, BigInteger> {
    TppProduct findFirstByNumber(String contractNumber);

}
