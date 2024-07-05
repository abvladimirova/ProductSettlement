package app.productregister.api;

import app.productregister.TppProductRegister;

public class CreateProductRegisterResponse {
    private final String accountId;
    public CreateProductRegisterResponse(TppProductRegister productRegister) {
        accountId = String.valueOf(productRegister.getId());
    }
}
