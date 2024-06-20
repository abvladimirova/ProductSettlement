package app.product.repository;

import org.springframework.data.repository.CrudRepository;
import app.product.entity.Agreement;

public interface AgreementRepo extends CrudRepository<Agreement, Integer> {

}
