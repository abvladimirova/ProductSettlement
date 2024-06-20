package app.productregister.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import app.common.response.BusinessErrorResponse;

@ControllerAdvice
public class ProductRegisterAdvice {
    @ResponseBody
    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<BusinessErrorResponse> employeeNotFoundHandler(DuplicateRecordException ex) {
        return new ResponseEntity<>(new BusinessErrorResponse(ex.getCode(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
