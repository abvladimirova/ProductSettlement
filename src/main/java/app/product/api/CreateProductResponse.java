package app.product.api;

import app.agreement.Agreement;
import app.product.TppProduct;
import app.productregister.TppProductRegister;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@ToString
public class CreateProductResponse {
    private String instanceId;
    private List<Integer> supplementaryAgreementId = new ArrayList<>();
    private List<Integer> registerId = new ArrayList<>();

    public CreateProductResponse(
            TppProduct product,
            List<TppProductRegister> productRegisters,
            List<Agreement> agreements)
    {
        instanceId = String.valueOf(product.getId());

        if (!Objects.isNull(productRegisters))
            registerId = productRegisters.stream().map((TppProductRegister::getId)).toList();
        if (!Objects.isNull(agreements))
            supplementaryAgreementId = agreements.stream().map((Agreement::getId)).toList();
    }
}
