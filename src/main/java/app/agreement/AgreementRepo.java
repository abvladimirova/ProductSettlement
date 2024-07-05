package app.agreement;

import org.springframework.data.repository.CrudRepository;

public interface AgreementRepo extends CrudRepository<Agreement, Integer> {

    Agreement findByNumber(String agreementNumber);
}
