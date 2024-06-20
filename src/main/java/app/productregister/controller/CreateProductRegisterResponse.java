package app.productregister.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;
import app.productregister.controller.CreateProductRegisterResponseData;

@AllArgsConstructor
@ToString
@Builder
public class CreateProductRegisterResponse {
    CreateProductRegisterResponseData data;
}
