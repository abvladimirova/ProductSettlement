package app.controllers;

import app.productregister.api.CreateProductRegisterRequest;
import app.productregister.api.CreateProductRegisterResponse;
import app.productregister.api.ProductRegisterApi;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductRegisterController {

    private final ProductRegisterApi productRegisterApi;

    public ProductRegisterController(ProductRegisterApi productRegisterApi) {
        this.productRegisterApi = productRegisterApi;
    }


    @PostMapping (value = "/corporate-settlement-account/create", consumes = "application/json", produces = "application/json")
    @Transactional
    public ResponseEntity<CreateProductRegisterResponse> createProductRegister(@Valid @RequestBody final CreateProductRegisterRequest request)
    {
        CreateProductRegisterResponse response = productRegisterApi.createProductRegister(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }

}


