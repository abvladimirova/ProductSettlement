package app.common.exceptions;

import lombok.Getter;

@Getter
public class NoDataFoundException extends RuntimeException {
    private final String code = "NO_DATA_FOUND";
    public NoDataFoundException(String message) {
        super(message);
    }}
