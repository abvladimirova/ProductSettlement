package app.agreement;

import org.springframework.data.repository.CrudRepository;
import app.agreement.Agreement;

public interface AgreementRepo extends CrudRepository<Agreement, Integer> {
    boolean existsByNumber(String agreementNumber);

    Agreement findByNumber(String agreementNumber);
}
