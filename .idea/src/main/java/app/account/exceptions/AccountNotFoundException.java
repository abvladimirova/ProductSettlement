package app.account.exceptions;

import lombok.Getter;

@Getter
public class AccountNotFoundException extends Exception {
    private final String code = "ACCOUNT_NOT_FOUND";
    public AccountNotFoundException(String message) {
        super(message);
    }
}