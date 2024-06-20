package app.productregister.exceptions;

import lombok.Getter;

@Getter
public class DuplicateRecordException extends RuntimeException {
    private final String code = "DUPLICATE_RECORD_EXISTS";
    public DuplicateRecordException(String message) {
        super(message);
    }}
