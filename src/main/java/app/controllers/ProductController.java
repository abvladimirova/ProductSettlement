package app.controllers;

import app.product.api.CreateProductRequest;
import app.product.api.CreateProductResponse;
import app.product.api.ProductApi;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductApi productApi;

    public ProductController(ProductApi productApi) {
        this.productApi = productApi;
    }

    @PostMapping(value = "/corporate-settlement-instance/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody final @Valid CreateProductRequest request) {

        CreateProductResponse response = productApi.createProduct(request);

        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}
