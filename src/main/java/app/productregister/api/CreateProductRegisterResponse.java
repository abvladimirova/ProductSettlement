package app.productregister.api;

import app.productregister.TppProductRegister;
import lombok.Getter;

@Getter
public class CreateProductRegisterResponse {
    private final String accountId;
    public CreateProductRegisterResponse(TppProductRegister productRegister) {
        accountId = String.valueOf(productRegister.getId());
    }
}
