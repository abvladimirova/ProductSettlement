package app.common.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class CommonExceptionHandler {
    @ResponseBody
    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> DuplicateRecordExceptionHandler(DuplicateRecordException ex) {
        final List<Error> violations = List.of(new Error(ex.getCode(), ex.getMessage()));
        return new ResponseEntity<>(new ErrorResponse(violations), HttpStatus.BAD_REQUEST);
    }
    @ResponseBody
    @ExceptionHandler(NoDataFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> NoDataFoundExceptionHandler(NoDataFoundException ex) {
        final List<Error> violations = List.of(new Error(ex.getCode(), ex.getMessage()));
        return new ResponseEntity<>(new ErrorResponse(violations),HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e
    ) {
        final List<Error> violations = e.getBindingResult().getFieldErrors().stream()
                .map(error -> new Error(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(violations), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> onConstraintValidationException(
            ConstraintViolationException e
    ) {
        final List<Error> violations = e.getConstraintViolations().stream()
                .map(
                        violation -> new Error(
                                violation.getPropertyPath().toString(),
                                violation.getMessage()
                        )
                )
                .collect(Collectors.toList());
        return new ResponseEntity<>(new ErrorResponse(violations), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handle(Exception ex) {
        final List<Error> violations = List.of(new Error("INTERNAL_SERVER_ERROR", ex.getClass().getName() + ": " + ex.getMessage()));
        return new ResponseEntity<>(new ErrorResponse(violations), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
