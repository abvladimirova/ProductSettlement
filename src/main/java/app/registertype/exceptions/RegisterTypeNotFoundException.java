package app.registertype.exceptions;

import lombok.Getter;

@Getter
public class RegisterTypeNotFoundException extends Exception {
    private final String code = "REGISTERTYPE_NOT_FOUND";
    public RegisterTypeNotFoundException(String message) {
        super(message);
    }
}
