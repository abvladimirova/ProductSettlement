package app.product.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import app.common.response.BusinessErrorResponse;



@ControllerAdvice
public class ProductAdvice {
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<BusinessErrorResponse> productNotFoundHandler(ProductNotFoundException ex) {
        return new ResponseEntity<>(new BusinessErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
