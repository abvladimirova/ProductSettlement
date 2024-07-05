package app.product.api;

import app.agreement.Agreement;
import app.product.TppProduct;
import app.productregister.TppProductRegister;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProductResponse {
    private final String instanceId;
    private final List<Integer> supplementaryAgreementId;
    private final List<Integer> registerId;

    public CreateProductResponse(
            TppProduct product,
            List<TppProductRegister> productRegisters,
            List<Agreement> agreements)
    {
        instanceId = String.valueOf(product.getId());

        registerId = productRegisters.stream().map((r -> r.getId())).toList();
        supplementaryAgreementId = agreements.stream().map((a -> a.getId())).toList();
    }
}
