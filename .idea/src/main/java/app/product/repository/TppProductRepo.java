package app.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import app.product.entity.TppProduct;


@Repository
public interface TppProductRepo extends CrudRepository<TppProduct, Integer> {

}
