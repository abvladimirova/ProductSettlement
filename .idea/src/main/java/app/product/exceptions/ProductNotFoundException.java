package app.product.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {
    private final String code = "PRODUCT_NOT_FOUND";
    public ProductNotFoundException(String message) {
        super(message);
    }
}
